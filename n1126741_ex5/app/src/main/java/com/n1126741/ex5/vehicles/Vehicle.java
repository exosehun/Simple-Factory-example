package com.n1126741.ex5.vehicles;

public abstract class Vehicle {          // 抽象(abstract)類別 Vehicle
    public static int totalIncome = 0;   // 追蹤所有交通工具總收入
    public static int totalCount = 0;    // 追蹤所有交通工具總數量

    public Vehicle() {
        totalCount++;
    }   // 建構式，交通工具實例化會被呼叫-->增加 totalCount，記錄已實例化交通工具數量。

    public static int getTotalCount() {  // 取得所有交通工具的總數量
        return totalCount;
    }

    public abstract int getCount();      // (抽象)實例後，返回該交通工具的數量


    public abstract String getType();    // (抽象)實例後，返回該交通工具的種類
}
