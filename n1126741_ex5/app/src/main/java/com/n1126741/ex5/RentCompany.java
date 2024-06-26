package com.n1126741.ex5;

import com.n1126741.ex5.vehicles.Airplane;
import com.n1126741.ex5.vehicles.Bicycle;
import com.n1126741.ex5.vehicles.Car;
import com.n1126741.ex5.vehicles.Motocycle;
import com.n1126741.ex5.vehicles.Rocket;
import com.n1126741.ex5.vehicles.Vehicle;

public class RentCompany {                     // 租賃公司-->factory
    private int totalRentAmount = 0;           // 總租金金額

    public Vehicle rent(int price) {           // 依照租金決定要租何種交通工具
        Vehicle vehicle;
        if (price >= 100 && price < 500) {
            vehicle = new Bicycle();
        } else if (price < 2000) {
            vehicle = new Motocycle();
        } else if (price < 30000) {
            vehicle = new Car();
        } else if (price < 100000) {
            vehicle = new Airplane();
        } else {
            vehicle = new Rocket();
        }
        totalRentAmount += price;
        return vehicle;
    }

    public int getTotalRentAmount() {  // 獲取總租金收入
        return totalRentAmount;
    }

    public void reset() {             // 重置歸零
        totalRentAmount = 0;
        Vehicle.totalIncome = 0;
        Vehicle.totalCount = 0;
        Bicycle.resetCount();
        Motocycle.resetCount();
        Car.resetCount();
        Airplane.resetCount();
        Rocket.resetCount();
    }
}
