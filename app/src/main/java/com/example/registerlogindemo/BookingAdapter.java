package com.example.registerlogindemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.BookingViewHolder> {

    private List<Booking> bookingList;
    private Context context;

    public BookingAdapter(List<Booking> bookingList, Context context) {
        this.bookingList = bookingList;
        this.context = context;
    }

    public static class BookingViewHolder extends RecyclerView.ViewHolder {
        public TextView bookingID;
        public TextView customerName;
        public TextView pickupDate;
        public TextView returnDate;
        public TextView bookingStatus;

        public BookingViewHolder(View itemView) {
            super(itemView);
            bookingID = itemView.findViewById(R.id.bookingID);
            customerName = itemView.findViewById(R.id.customerName);
            pickupDate = itemView.findViewById(R.id.pickupDate);
            returnDate = itemView.findViewById(R.id.returnDate);
            bookingStatus = itemView.findViewById(R.id.bookingStatus);
        }
    }

    @NonNull
    @Override
    public BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.booking_card, parent, false);
        return new BookingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingViewHolder holder, int position) {
        Booking booking = bookingList.get(position);
        holder.bookingID.setText(String.valueOf(booking.getBookingId()));
        holder.customerName.setText(booking.getName());
        holder.pickupDate.setText(booking.getStartDate());
        holder.returnDate.setText(booking.getReturnDate());
        holder.bookingStatus.setText(booking.getActive());
    }

    @Override
    public int getItemCount() {
        return bookingList.size();
    }

    private String getBookingStatus(int active) {
        if (active == 1) {
            return "Confirmed";
        } else {
            return "Pending";
        }
    }
}
