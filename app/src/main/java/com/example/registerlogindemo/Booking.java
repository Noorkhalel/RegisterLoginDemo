package com.example.registerlogindemo;

public class Booking {
    private String bookingId;
    private String customerId;
    private String vehicleId;
    private String startDate;
    private String returnDate;
    private String numOfDays;
    private String amountDue;
    private String active;
    private String scheduled;
    private String email;
    private String phone;
    private String name;

    public Booking(String bookingId, String customerId, String vehicleId, String startDate, String returnDate, String numOfDays, String amountDue, String email, String phone, String name) {
        this.bookingId = bookingId;
        this.customerId = customerId;
        this.vehicleId = vehicleId;
        this.startDate = startDate;
        this.returnDate = returnDate;
        this.numOfDays = numOfDays;
        this.amountDue = amountDue;
        this.email = email;
        this.phone = phone;
        this.name = name;
    }

    // Getters
    public String getBookingId() {
        return bookingId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public String getNumOfDays() {
        return numOfDays;
    }


    public String getAmountDue() {
        return amountDue;
    }

    public String getActive() {
        return active;
    }

    public String getScheduled() {
        return scheduled;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getName() {
        return name;
    }

    // Setters
    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public void setNumOfDays(String numOfDays) {
        this.numOfDays = numOfDays;
    }

    public void setAmountDue(String amountDue) {
        this.amountDue = amountDue;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public void setScheduled(String scheduled) {
        this.scheduled = scheduled;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setName(String name) {
        this.name = name;
    }
}
