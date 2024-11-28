package com.thi.btl_gplx.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

import com.thi.btl_gplx.R;


public class Welcome extends AppCompatActivity {

    private static final int DELAY = 2500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(Welcome.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, DELAY);
    }
}
