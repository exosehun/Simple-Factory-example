package com.n1126741.ex5;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.n1126741.ex5.vehicles.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    private RentCompany rentCompany;        // 租賃公司實例
    private EditText etSimulationCount;     // 用戶輸入模擬次數的EditText
    private Button btnSimulate;             // 觸發模擬的按鈕
    private ResultAdapter adapter;          // 定義的Adapter，用於將結果顯示在列表中
    private List<ResultAdapter.SimulationResult> results; // 儲存模擬結果的列表
    // TextView 元素引用
    private TextView tvSimulationNumber;
    private TextView tvRentalPrice;
    private TextView tvVehicleType;
    private TextView tvAttendanceRate;
    private TextView tvTotalRentAmount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // UI元素匹配
        etSimulationCount = findViewById(R.id.editTextNumber);
        btnSimulate = findViewById(R.id.buttonSimulate);
        // 顯示模擬結果的ListView
        ListView lvResults = findViewById(R.id.listView);
        // TextView元素匹配
        tvSimulationNumber = findViewById(R.id.tvSimulationNumber);
        tvRentalPrice = findViewById(R.id.tvRentalPrice);
        tvVehicleType = findViewById(R.id.tvVehicleType);
        tvAttendanceRate = findViewById(R.id.tvAttendanceRate);
        tvTotalRentAmount = findViewById(R.id.tvTotalRentAmount);

        rentCompany = new RentCompany();  // 租賃公司具象化
        results = new ArrayList<>();
        adapter = new ResultAdapter(this, results);
        lvResults.setAdapter(adapter);

        etSimulationCount.addTextChangedListener(new TextWatcher() {  // 設置文本監聽器以根據輸入啟用/禁用模擬按鈕
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                // 根據EditText是否為空設置按鈕狀態
                btnSimulate.setEnabled(!s.toString().isEmpty());
            }
        });

        // 設置按鈕監聽器
        btnSimulate.setOnClickListener(v -> {
            if (etSimulationCount.getText().toString().isEmpty()) {
                Toast.makeText(MainActivity.this, "請輸入模擬次數", Toast.LENGTH_SHORT).show();
            } else {
                simulateRentals();
            }
        });
    }

    private void simulateRentals() { // 模擬租賃的方法
        results.clear();     // 清空之前的結果
        rentCompany.reset(); // 重置租賃公司，清空數據

        int num = Integer.parseInt(etSimulationCount.getText().toString());
        Random random = new Random();
        for (int i = 1; i <= num; i++) {          // 進行租賃模擬
            int category = random.nextInt(5) + 1; // 讓亂數產生較平均的設置
            int rentalPrice = 0;

            switch (category) {
                case 1:
                    rentalPrice = random.nextInt(400) + 100; // 100-499
                    break;
                case 2:
                    rentalPrice = random.nextInt(1500) + 500; // 500-1999
                    break;
                case 3:
                    rentalPrice = random.nextInt(28000) + 2000; // 2000-29999
                    break;
                case 4:
                    rentalPrice = random.nextInt(70000) + 30000; // 30000-99999
                    break;
                case 5:
                    rentalPrice = random.nextInt(100001) + 100000; // 100000-200000
                    break;
            }
            // 租賃交通工具
            Vehicle rentedVehicle = rentCompany.rent(rentalPrice);
            // 計算出勤率
            double vehicleCount = rentedVehicle.getCount();
            double totalVehicleCount = Vehicle.getTotalCount();
            double attendanceRate = (vehicleCount / totalVehicleCount) * 100;
            ResultAdapter.SimulationResult result = new ResultAdapter.SimulationResult(  // 建立模擬結果實例
                    i,
                    rentalPrice,
                    rentedVehicle.getType(),
                    attendanceRate,
                    rentCompany.getTotalRentAmount()
            );
            results.add(result);
        }
        // 模擬完成，設置TextView為可見
        if (!results.isEmpty()) {
            tvSimulationNumber.setVisibility(View.VISIBLE);
            tvRentalPrice.setVisibility(View.VISIBLE);
            tvVehicleType.setVisibility(View.VISIBLE);
            tvAttendanceRate.setVisibility(View.VISIBLE);
            tvTotalRentAmount.setVisibility(View.VISIBLE);
        }

        adapter.notifyDataSetChanged();     // 更新ListView
    }

}