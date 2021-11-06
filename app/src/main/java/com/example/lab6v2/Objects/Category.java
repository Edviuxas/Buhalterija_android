package com.example.lab6v2.Objects;

import java.io.Serializable;
import java.util.ArrayList;

public class Category implements Serializable {
    private String name = "--";
    private String description;
    private String parentName = null;
    private double sumOfExpenses = 0;
    private double sumOfIncomes = 0;
    private String path = "";

    public Category(String name, String description, double sumOfExpenses, double sumOfIncomes) {
        this.name = name;
        this.description = description;
        this.sumOfExpenses = sumOfExpenses;
        this.sumOfIncomes = sumOfIncomes;
    }

    public Category(String name, String description, String parentName, double sumOfExpenses, double sumOfIncomes) {
        this.name = name;
        this.description = description;
        this.parentName = parentName;
        this.sumOfExpenses = sumOfExpenses;
        this.sumOfIncomes = sumOfIncomes;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public double getSumOfExpenses() {
        return sumOfExpenses;
    }

    public void setSumOfExpenses(double sumOfExpenses) {
        this.sumOfExpenses = sumOfExpenses;
    }

    public double getSumOfIncomes() {
        return sumOfIncomes;
    }

    public void setSumOfIncomes(double sumOfIncomes) {
        this.sumOfIncomes = sumOfIncomes;
    }

    public Category() {
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

    @Override
    public String toString() {
        return this.getName() + "(" + path + ")";
    }
}
