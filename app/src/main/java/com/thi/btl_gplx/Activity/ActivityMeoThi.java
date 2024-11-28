package com.thi.btl_gplx.Activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.thi.btl_gplx.R;


public class ActivityMeoThi extends AppCompatActivity {

    private TextView section1, section2, section3, section4, section5;
    private ScrollView scrollable;
    private boolean isFirstTime = true;
    private Button buttonBackToTop;  // Nút quay lại đầu trang

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meothi);

        // Button quay lại đầu trang
        buttonBackToTop = findViewById(R.id.button_back_to_top);
        buttonBackToTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cuộn mượt về đầu trang khi nhấn nút
                scrollable.smoothScrollTo(0, 0);
            }
        });

        // Các phần tử khác
        Button button = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Thực hiện hành động khi Button được nhấn
                onBackPressed(); // Quay lại Activity trước đó
            }
        });

        Spinner spinner = findViewById(R.id.spinner_select_chapter);
        scrollable = findViewById(R.id.scrollable);
        section1 = findViewById(R.id.text_section1);
        section2 = findViewById(R.id.text_section2);
        section3 = findViewById(R.id.text_section3);
        section4 = findViewById(R.id.text_section4);
        section5 = findViewById(R.id.text_section5);

        String[] chapters = {
                "I. CÁC KHÁI NIỆM CƠ BẢN",
                "II. QUY TẮC GIAO THÔNG ĐƯỜNG BỘ",
                "III. TỐC ĐỘ TỐI ĐA CHO PHÉP",
                "IV. LƯU Ý KHI THAM GIA GIAO THÔNG",
                "V. KẾT LUẬN",
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.custom_spinner_item, chapters);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);  // Dropdown item layout
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Chỉ xử lý khi người dùng chọn mục (bỏ qua lần chọn đầu tiên)
                if (!isFirstTime) {
                    String selectedChapter = chapters[position];
                    Toast.makeText(ActivityMeoThi.this, "Chọn: " + selectedChapter, Toast.LENGTH_SHORT).show();
                    scrollToView(getSectionViewByPosition(position));
                }
                isFirstTime = false;  // Đặt cờ thành false sau lần đầu tiên
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        // Khi người dùng cuộn xuống, hiển thị nút quay lại đầu trang
        scrollable.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                if (scrollable.getScrollY() > 200) {
                    buttonBackToTop.setVisibility(View.VISIBLE); // Hiển thị nút khi cuộn xuống
                } else {
                    buttonBackToTop.setVisibility(View.GONE); // Ẩn nút khi ở đầu trang
                }
            }
        });
    }

    // Scroll to the selected section
    private void scrollToView(View view) {
        if (view != null) {
            scrollable.smoothScrollTo(0, (int) view.getY());
        }
    }

    private View getSectionViewByPosition(int position) {
        switch (position) {
            case 0: return section1;
            case 1: return section2;
            case 2: return section3;
            case 3: return section4;
            case 4: return section5;
            default: return null;
        }
    }
}
