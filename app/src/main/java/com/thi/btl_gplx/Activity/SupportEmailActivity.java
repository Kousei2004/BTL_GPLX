package com.thi.btl_gplx.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

import com.thi.btl_gplx.R;


public class SupportEmailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ho_tro);

        ImageButton imgBtnHome = findViewById(R.id.img_btnHome);

        imgBtnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SupportEmailActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}

