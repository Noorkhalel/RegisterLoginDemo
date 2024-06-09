package com.example.registerlogindemo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;

import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarViewHolder> {
    private List<Car> carList;

    public static class CarViewHolder extends RecyclerView.ViewHolder {
        public ImageView carImage;
        public TextView carName;
        public TextView carYear;
        public TextView carPrice;
        public TextView carModel;
        public TextView carAvailableStart;
        public TextView carAvailableEnd;
        public TextView carOwnerId;

        public CarViewHolder(View itemView) {
            super(itemView);
            carImage = itemView.findViewById(R.id.car_image);
            carName = itemView.findViewById(R.id.car_name);
            carYear = itemView.findViewById(R.id.car_year);
            carPrice = itemView.findViewById(R.id.car_price);
            carModel = itemView.findViewById(R.id.car_model);
            carAvailableStart = itemView.findViewById(R.id.car_available_start);
            carAvailableEnd = itemView.findViewById(R.id.car_available_end);
            carOwnerId = itemView.findViewById(R.id.car_owner_id);
        }
    }

    public CarAdapter(List<Car> carList) {
        this.carList = carList;
    }

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_car, parent, false);
        return new CarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {
        Car car = carList.get(position);
        holder.carName.setText(car.getName());
        holder.carYear.setText(String.valueOf(car.getYear()));
        holder.carPrice.setText(String.format("$%s", car.getPrice()));
        holder.carModel.setText(car.getModel());
        holder.carAvailableStart.setText(car.getAvailableStart());
        holder.carAvailableEnd.setText(car.getAvailableEnd());
        holder.carOwnerId.setText(String.valueOf(car.getOwnerId()));

        // Load the image using Glide
        Glide.with(holder.itemView.getContext())
                .load(car.getImageUrl())
                .into(holder.carImage);
    }

    @Override
    public int getItemCount() {
        return carList.size();
    }
}
