package com.vrp.application.model;

public class Vehicle {
    private String id;
    private int capacity;
    private int currentLoad;

    public Vehicle(String id, int capacity) {
        this.id = id;
        this.capacity = capacity;
        this.currentLoad = 0;
    }

    public boolean canServe(int demand) {
        return (currentLoad + demand) <= capacity;
    }

    public void addLoad(int demand) {
        this.currentLoad += demand;
    }

    public int getCurrentLoad() {
        return currentLoad;
    }

    public String getId() {
        return id;
    }

    public int getCapacity() {
        return capacity;
    }

    public double getOccupancyPercentage() {
        return (currentLoad / (double) capacity) * 100;
    }

    public void resetLoad() {
        currentLoad = 0;
    }
}
