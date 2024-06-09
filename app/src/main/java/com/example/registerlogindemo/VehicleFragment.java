package com.example.registerlogindemo;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
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

public class VehicleFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<Car> carList;
    private CarAdapter adapter;

    public VehicleFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_vehicle, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        carList = new ArrayList<>();
        adapter = new CarAdapter(carList);
        recyclerView.setAdapter(adapter);

        // Fetch data from PHP backend
        fetchDataFromPHP();

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
                            jsonObject.getInt("ownerId")
                    );
                    carList.add(car);
                }

                // Update UI on the main thread
                requireActivity().runOnUiThread(() -> adapter.notifyDataSetChanged());

            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
