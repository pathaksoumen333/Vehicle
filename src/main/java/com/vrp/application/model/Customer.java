package com.vrp.application.model;

public class Customer {
    private String name;
    private int demand;

    public Customer(String name, int demand) {
        this.name = name;
        this.demand = demand;
    }

    public String getName() {
        return name;
    }

    public int getDemand() {
        return demand;
    }
}
