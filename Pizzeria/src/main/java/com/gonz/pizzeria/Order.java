package com.gonz.pizzeria;

import java.util.List;

public class Order {
    private int id;
    private List<Pizza> pizzas;
    private String customerName;
    private String address;
    private String phoneNumber;
    private double totalCost;

    public Order(int id, List<Pizza> pizzas, String customerName, String address, String phoneNumber) {
        this.id = id;
        this.pizzas = pizzas;
        this.customerName = customerName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        //calculateTotalCost();
    }

    private void calculateTotalCost() {
        totalCost = 0;
        for (Pizza pizza : pizzas) {
            totalCost += pizza.getSize() * 0.5 + pizza.getExtras().size() * 1;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
        //calculateTotalCost();
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getTotalCost() {
        return totalCost;
    }
}
