package com.example.registerlogindemo;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CarOwner extends Fragment {

    private static final String ARG_USER_ID = "user_id";
    private static final String ARG_USER_TYP = "user_typ";
    private CarAdapter adapter;
    private static String userId, usertyp;
    private RecyclerView recyclerView;
    private List<Car> carList;

    public CarOwner() {
        // Required empty public constructor
    }

    public static CarOwner newInstance(String userId,String usertyp) {
        CarOwner fragment = new CarOwner();
        Bundle args = new Bundle();
        args.putString(ARG_USER_ID, userId);
        args.putString(ARG_USER_TYP, usertyp);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userId = getArguments().getString(ARG_USER_ID);
            usertyp = getArguments().getString(ARG_USER_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_car_owner, container, false);

        recyclerView = view.findViewById(R.id.recycler_view_vehicle2);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        carList = new ArrayList<>();

        if (userId != null) {
            adapter = new CarAdapter(carList, requireContext(), userId,"O");
            recyclerView.setAdapter(adapter);
            fetchDataFromPHP();
        }

        return view;
    }

    private void fetchDataFromPHP() {
        new Thread(() -> {
            try {
                // URL of your PHP script
                URL url = new URL("http://10.0.2.2/PHP_Android/get_cars.php");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                // Read response
                InputStream inputStream = conn.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                bufferedReader.close();
                inputStream.close();

                // Parse JSON response
                JSONArray jsonArray = new JSONArray(stringBuilder.toString());
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Car car = new Car(
                            jsonObject.getString("name"),
                            jsonObject.getInt("year"),
                            jsonObject.getInt("price"),
                            jsonObject.getString("imageUrl"),
                            jsonObject.getInt("vehicleId"),
                            jsonObject.getString("model"),
                            jsonObject.getString("availableStart"),
                            jsonObject.getString("availableEnd"),
                            jsonObject.getInt("ownerId"),
                            jsonObject.getInt("availability")
                    );
                    carList.add(car);
                }

                // Update UI on the main thread
                new Handler(Looper.getMainLooper()).post(() -> {
                    if (isAdded()) {
                        adapter.notifyDataSetChanged();
                    }
                });

            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
