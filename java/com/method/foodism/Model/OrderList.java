package com.method.foodism.Model;

public class OrderList {

    public String name;
    public int cost;
    public int counter;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getCost() {
        return cost;
    }
    public void setCost(int cost) {
        this.cost = cost;
    }
    public int getCounter() {
        return counter;
    }
    public void setCounter(int counter) {
        this.counter = counter;
    }

    @Override
    public String toString() {
        return "OrderList{" +
                "name='" + name + '\'' +
                ", cost=" + cost +
                '}';
    }
}
