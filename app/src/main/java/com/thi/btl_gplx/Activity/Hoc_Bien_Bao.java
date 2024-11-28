package com.thi.btl_gplx.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;





import com.google.android.material.tabs.TabLayout;
import com.thi.btl_gplx.Adapter.ViewPagerAdapter;
import com.thi.btl_gplx.Models.TrafficSign;
import com.thi.btl_gplx.R;

import java.util.List;

public class Hoc_Bien_Bao extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private List<TrafficSign> data;

    private RecyclerView rcv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_hoc_bien_bao);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);
        Button button = findViewById(R.id.button2);

        ViewPagerAdapter view = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(view);
        tabLayout.setupWithViewPager(viewPager);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Thực hiện hành động khi Button được nhấn
                onBackPressed(); // Quay lại Activity trước đó
            }
        });
    }
}