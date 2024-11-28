package com.thi.btl_gplx.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


import com.google.android.material.navigation.NavigationView;
import com.thi.btl_gplx.R;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerlayout;
    NavigationView navigationView;
    private static final String TAG = "NavigationView";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Kết nối DrawerLayout và NavigationView
        drawerlayout = findViewById(R.id.drawerlayout);
        navigationView = findViewById(R.id.navigationView);

        // Liên kết các RelativeLayout
        findViewById(R.id.cv_hocbienbao).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Hoc_Bien_Bao.class);
            startActivity(intent);
        });

        findViewById(R.id.cv_hoclythuyet).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ActivityHocLyThuyet.class);
            startActivity(intent);
        });

        findViewById(R.id.cv_meoonthi).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ActivityMeoThi.class);
            startActivity(intent);
        });

        findViewById(R.id.cv_baithithuchanh).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Bai_Thi_Thuc_Hanh.class);
            startActivity(intent);
        });

        findViewById(R.id.cv_thisathach).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Activity_De_Thi.class);
            startActivity(intent);
        });

        findViewById(R.id.cv_50cauhaysai).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, FavoriteQuestionsActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.cv_tracuuluat).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Activity_De_Thi_Db.class);
            startActivity(intent);
        });


        findViewById(R.id.cv_nhomonthi).setOnClickListener(v -> {
            String facebookUrl = "https://www.facebook.com/groups/363387201388480/?hoisted_section_header_type=recently_seen&multi_permalinks=1205974573796401";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(facebookUrl));
            startActivity(intent);
        });


        // Cấu hình Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

// Khởi tạo DrawerLayout và NavigationView
        DrawerLayout drawerLayout = findViewById(R.id.drawerlayout);
        NavigationView navigationView = findViewById(R.id.navigationView);

// Cài đặt sự kiện click cho Navigation icon trên Toolbar
        toolbar.setNavigationOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));


        navigationView.setNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.navLogout) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            } else if (itemId == R.id.navEvaluate) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.thi.btl_gplx&pli=1"));
                startActivity(intent);
                return true;
            }else if (itemId == R.id.navShare) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                String shareMessage = "Hãy thử ứng dụng này ngay: \nhttps://play.google.com/store/apps/details?id=com.thi.btl_gplx&pli=1 " + getPackageName();
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                startActivity(Intent.createChooser(shareIntent, "Chia sẻ ứng dụng qua"));
           } else if (itemId == R.id.navFeedBack) {
                Intent intent = new Intent(MainActivity.this, SupportEmailActivity.class);
                startActivity(intent);
            } else if (itemId == R.id.navDelete) {

            }else if(itemId == R.id.navProfile){
                Intent intent = new Intent(MainActivity.this, ThongTinApp.class);
                startActivity(intent);
            }
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });
    }
    }
