package com.example.registerlogindemo;

import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingsFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<Booking> bookingList;
    private BookingAdapter bookingAdapter;
    TextView myBooking;
    private static String userIda;

    private static final String TAG = "BookingsFragment";

    public BookingsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bookings, container, false);

        recyclerView = view.findViewById(R.id.recycler_view_bookings); // Update RecyclerView ID
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        bookingList = new ArrayList<>();
        bookingAdapter = new BookingAdapter(bookingList, requireContext());
        recyclerView.setAdapter(bookingAdapter);
//        myBooking = view.findViewById(R.id.myBooking);
//        myBooking.setText(userIda);

        fetchDataFromPHP(userIda);

        return view;
    }

    public static BookingsFragment newInstance(String userId) {
        BookingsFragment fragment = new BookingsFragment();
        fragment.userIda = userId;
        return fragment;
    }

    private void fetchDataFromPHP(String userId) {
        String URL = "http://10.0.2.2/PHP_Android/get_bookings.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.d("Response", response);
                    JSONObject jsonResponse = new JSONObject(response);
                    if (jsonResponse.has("error")) {
                        // Handle error response
                        Log.e(TAG, jsonResponse.getString("error"));
                    } else {
                        // Parse the JSON array

                        JSONArray jsonArray = jsonResponse.getJSONArray("bookings");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            // Add logging to check if the booking is being parsed correctly
                            Log.d(TAG, "Booking: " + jsonObject.toString());
                            Booking booking = new Booking(
                                    jsonObject.getString("bookingId"),
                                    jsonObject.getString("customerId"),
                                    jsonObject.getString("vehicleId"),
                                    jsonObject.getString("startDate"),
                                    jsonObject.getString("returnDate"),
                                    jsonObject.getString("numOfDays"),
                                    jsonObject.getString("numOfWeeks"),
                                    jsonObject.getString("email"),
                                    jsonObject.getString("phone"),
                                    jsonObject.getString("name")
                            );
                            bookingList.add(booking);
                        }

                        requireActivity().runOnUiThread(() -> bookingAdapter.notifyDataSetChanged());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("user_id", userId);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(requireContext());
        requestQueue.add(stringRequest);
    }
}
