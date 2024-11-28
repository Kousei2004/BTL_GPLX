package com.thi.btl_gplx.Activity;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

import com.thi.btl_gplx.R;


public class ThongTinApp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_ung_dung);

        ImageButton imgBtnHome = findViewById(R.id.img_btnHome);

        imgBtnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThongTinApp.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}