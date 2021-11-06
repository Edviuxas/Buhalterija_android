package com.example.lab6v2.Objects;

public class Expense {
    private String name;
    private String description;
    private double amount;

    public Expense(String name, String description, double amount) {
        this.name = name;
        this.description = description;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Name: " + name + "; " + "Amount: " + amount;
    }
}
