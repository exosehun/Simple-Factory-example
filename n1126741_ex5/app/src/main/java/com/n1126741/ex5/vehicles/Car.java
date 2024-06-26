package com.n1126741.ex5.vehicles;

public class Car extends Vehicle {
    private static int count = 0;

    public Car() {
        super();  // Vehicle()
        count++;
    }

    public static void resetCount() {
        count = 0;
    }

    public int getCount() {
        return count;
    }

    @Override
    public String getType() {
        return "汽車";
    }
}
