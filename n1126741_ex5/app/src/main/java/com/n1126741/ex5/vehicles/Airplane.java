package com.n1126741.ex5.vehicles;

public class Airplane extends Vehicle {
    private static int count = 0;

    public Airplane() {
        super();  // Vehicle()
        count++;
    }

    public static void resetCount() {
        count = 0;
    }

    public int getCount() {
        return count;
    }


    public String getType() {
        return "飛機";
    }
}
