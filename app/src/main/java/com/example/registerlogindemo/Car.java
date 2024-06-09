package com.example.registerlogindemo;

public class Car {
    private String name;
    private int year;
    private int price;
    private String imageUrl;
    private int vehicleId;
    private String model;
    private String availableStart;
    private String availableEnd;
    private int ownerId;

    public Car() {
        // Default constructor required for calls to DataSnapshot.getValue(Car.class)
    }

    public Car(String name, int year, int price, String imageUrl, int vehicleId, String model, String availableStart, String availableEnd, int ownerId) {
        this.name = name;
        this.year = year;
        this.price = price;
        this.imageUrl = imageUrl;
        this.vehicleId = vehicleId;
        this.model = model;
        this.availableStart = availableStart;
        this.availableEnd = availableEnd;
        this.ownerId = ownerId;
    }

    public Car(String name, int year, double price, String model, String availableStart, String availableEnd, int ownerId, String imageUrl) {
    }

    // Getters and setters for all fields

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public int getVehicleId() { return vehicleId; }
    public void setVehicleId(int vehicleId) { this.vehicleId = vehicleId; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public String getAvailableStart() { return availableStart; }
    public void setAvailableStart(String availableStart) { this.availableStart = availableStart; }

    public String getAvailableEnd() { return availableEnd; }
    public void setAvailableEnd(String availableEnd) { this.availableEnd = availableEnd; }

    public int getOwnerId() { return ownerId; }
    public void setOwnerId(int ownerId) { this.ownerId = ownerId; }
}
