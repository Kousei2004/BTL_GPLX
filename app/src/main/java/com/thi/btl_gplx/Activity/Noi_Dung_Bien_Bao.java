package com.thi.btl_gplx.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.thi.btl_gplx.Models.TrafficSign;
import com.thi.btl_gplx.R;


public class Noi_Dung_Bien_Bao extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noi_dung_bien_bao);

        // Lấy dữ liệu từ Intent
        Intent intent = getIntent();
        if (intent != null) {
            TrafficSign selectedItem = (TrafficSign) intent.getSerializableExtra("itemData");
            if (selectedItem != null) {
                // Hiển thị thông tin của selectedItem trong Activity này
                TextView nameTextView = findViewById(R.id.textView);
                TextView descriptionTextView = findViewById(R.id.textView2);
                ImageView imageView = findViewById(R.id.imageView);
                Button button = findViewById(R.id.button2);

                button.setText("Biển "+ selectedItem.getCode());
                nameTextView.setText("Biển " + selectedItem.getCode()+": "+selectedItem.getName());
                descriptionTextView.setText(selectedItem.getDescription());
                imageView.setImageResource(selectedItem.getImageResId());
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Thực hiện hành động khi Button được nhấn
                        onBackPressed(); // Quay lại Activity trước đó
                    }
                });
            }
        }
    }
}