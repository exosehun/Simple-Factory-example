package com.n1126741.ex5;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ResultAdapter extends BaseAdapter {

    private final List<SimulationResult> results;    // 儲存模擬結果的列表
    private final LayoutInflater inflater; // 從XML文件中加載佈局的對象

    public ResultAdapter(Context context, List<SimulationResult> results) { // 將傳入的 results 資料儲存到類別的成員變數中，並初始化 inflater 變數
        this.results = results;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {   // List裡有多少筆資料
        return results.size();
    } // 回傳 List 裡有多少筆資料

    @Override
    public Object getItem(int position) {  // 回傳 results 位置
        return results.get(position);
    } // 回傳 results 位置

    @Override
    public long getItemId(int position) {  // 回傳 results 識別Id
        return position;
    } // 回傳 results 識別Id

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Override
    public View getView(int position, View convertView, ViewGroup parent) { // 位置、convertView-->已存在畫面，為減少記憶體使用、parnet-->畫面容器
        ViewHolder holder; // ViewHolder 保存物件
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item, parent, false); // convertView不為空，加入list_item.xml佈局檔
            holder = new ViewHolder();
            holder.tvSimulationNumber = convertView.findViewById(R.id.tvSimulationNumber);
            holder.tvRentalPrice = convertView.findViewById(R.id.tvRentalPrice);
            holder.tvVehicleType = convertView.findViewById(R.id.tvVehicleType);
            holder.tvAttendanceRate = convertView.findViewById(R.id.tvAttendanceRate);
            holder.tvTotalRentAmount = convertView.findViewById(R.id.tvTotalRentAmount);
            convertView.setTag(holder);
        } else {  // 否則取的holder
            holder = (ViewHolder) convertView.getTag();
        }
        SimulationResult result = (SimulationResult) getItem(position);             // 取 SimulationResult 的位置
        // 設置 TextView 的內容
        holder.tvSimulationNumber.setText("No." + result.getSimulationNumber());
        holder.tvRentalPrice.setText("" + result.getRentalPrice());
        holder.tvVehicleType.setText(result.getVehicleType());
        holder.tvAttendanceRate.setText(String.format("%.1f", result.getAttendanceRate()) + "%");
        holder.tvTotalRentAmount.setText("" + result.getTotalRentAmount());

        return convertView;
    }

    private static class ViewHolder {       // 保存 TextView 各個結果
        TextView tvSimulationNumber;
        TextView tvRentalPrice;
        TextView tvVehicleType;
        TextView tvAttendanceRate;
        TextView tvTotalRentAmount;

    }

    public static class SimulationResult {    // 模擬5個值
        private final int simulationNumber;   // 模擬次數
        private final int rentalPrice;        // 租金
        private final String vehicleType;     // 交通工具
        private final double attendanceRate;  // 出勤率
        private final int totalRentAmount;    // 總租金金額

        // 建構函數
        public SimulationResult(int simulationNumber, int rentalPrice, String vehicleType, double attendanceRate, int totalRentAmount) {
            this.simulationNumber = simulationNumber;
            this.rentalPrice = rentalPrice;
            this.vehicleType = vehicleType;
            this.attendanceRate = attendanceRate;
            this.totalRentAmount = totalRentAmount;
        }

        public int getSimulationNumber() {      // 模擬次數 getter 方法
            return simulationNumber;
        }

        public int getRentalPrice() {           // 租金 getter 方法
            return rentalPrice;
        }

        public String getVehicleType() {        // 交通工具 getter 方法
            return vehicleType;
        }

        public double getAttendanceRate() {     // 出勤率 getter 方法
            return attendanceRate;
        }

        public int getTotalRentAmount() {       // 總租金金額 getter 方法
            return totalRentAmount;
        }
    }
}
