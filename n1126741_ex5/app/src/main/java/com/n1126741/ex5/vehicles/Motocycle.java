package com.n1126741.ex5.vehicles;

public class Motocycle extends Vehicle {
    private static int count = 0;

    public Motocycle() {
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
        return "機車";
    }
}
