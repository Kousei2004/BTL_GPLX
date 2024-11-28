package com.thi.btl_gplx.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.thi.btl_gplx.R;


public class Bai_Thi_Thuc_Hanh extends AppCompatActivity {
    private ScrollView scrollView;
    private TextView section1, section2, section3, section4;
    private boolean isFirstTime = true;
    private Button buttonBackToTop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bai_thi_thuc_hanh);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.scrollView), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Đặt nội dung cho TextView
        TextView textViewI = findViewById(R.id.textViewI);
        textViewI.setText(getLyThuyetTextI());

        TextView textViewII = findViewById(R.id.textViewII);
        textViewII.setText(getLyThuyetTextII());

        TextView textViewIII = findViewById(R.id.textViewIII);
        textViewIII.setText(getLyThuyetTextIII());

        TextView textViewIV = findViewById(R.id.textViewIV);
        textViewIV.setText(getLyThuyetTextIV());

        // Khởi tạo ScrollView và Button
        scrollView = findViewById(R.id.scrollView);
        buttonBackToTop = findViewById(R.id.button_back_to_top);
        buttonBackToTop.setOnClickListener(v -> scrollView.smoothScrollTo(0, 0));

        Button button = findViewById(R.id.button2);
        button.setOnClickListener(v -> onBackPressed());

        ImageView imageViewI = findViewById(R.id.imageViewI);
        imageViewI.setImageResource(R.drawable.img_200);

        // Khởi tạo Spinner và TextView cho các section
        Spinner spinner = findViewById(R.id.spinner_select_chapter);
        section1 = findViewById(R.id.text_section1);
        section2 = findViewById(R.id.text_section2);
        section3 = findViewById(R.id.text_section3);
        section4 = findViewById(R.id.text_section4);

        String[] chapters = {
                "I. BÀI THI THỰC HÀNH SỐ 1",
                "II. BÀI THI THỰC HÀNH SỐ 2",
                "III. BÀI THI THỰC HÀNH SỐ 3",
                "IV. BÀI THI THỰC HÀNH SỐ 4",
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
                    Toast.makeText(Bai_Thi_Thuc_Hanh.this, "Chọn: " + selectedChapter, Toast.LENGTH_SHORT).show();
                    scrollToView(getSectionViewByPosition(position));  // Cuộn đến section tương ứng
                }
                isFirstTime = false;  // Đặt cờ thành false sau lần đầu tiên
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    // Phương thức trả về View tương ứng với từng section
    private View getSectionViewByPosition(int position) {
        switch (position) {
            case 0:
                return section1;  // "I. BÀI THI THỰC HÀNH SỐ 1"
            case 1:
                return section2;  // "II. BÀI THI THỰC HÀNH SỐ 2"
            case 2:
                return section3;  // "III. BÀI THI THỰC HÀNH SỐ 3"
            case 3:
                return section4;  // "IV. BÀI THI THỰC HÀNH SỐ 4"
            default:
                return null;
        }
    }

    // Phương thức cuộn đến vị trí của view cần thiết
    private void scrollToView(View view) {
        if (view != null) {
            // Lấy tọa độ Y của view cần cuộn tới
            int scrollToY = view.getTop();
            // Cuộn đến vị trí Y của view
            scrollView.smoothScrollTo(0, scrollToY);
        }
    }



    private String getLyThuyetTextI() {
        return "Bài số 1: Đi qua hình số 8\n" +
                "Hướng dẫn cách thi:\n" +
                "\n" +
                "Bạn phải nghe hiệu lệnh từ giám khảo phát trên Loa. Khi có lệnh xuất phát mới được cho xe chạy.\n" +
                "Khi có hiệu lệnh xuất phát, điều khiển xe tiến đến cửa vào hình số 8, rẽ phải đi một vòng hình số 8 (vạch đứt đỏ).\n" +
                "Sau đó đi hình số 3 ngược (hình đứt đen)\n" +
                "Tiếp tục thực hiện bài số 2.\n" +
                "\n" +
                "Kích thước hình số 8:\n" +
                "\n" +
                "- Bán kính vòng ngoài là: 3 m\n" +
                "- Bán kính vòng trong là: 2,3 m\n" +
                "- Khoảng cách giữa hai tâm vòng tròn trong là: 5,7 m\n" +
                "\n" +
                "Quy định xử lý lỗi:\n" +
                "\n" +
                "- Chạm vạch một lần trừ 05 điểm\n" +
                "- Chống chân 01 lần trừ 05 điểm\n" +
                "- Xe chết máy mỗi lần trừ 05 điểm\n" +
                "- Đi ngược hình trừ 25 điểm (trượt luôn)\n" +
                "- Hai bánh ra khỏi hình thi trừ 25 điểm (trượt luôn)\n";
    }


    private String getLyThuyetTextII() {
        return "2. Bài thi Dừng xe và nhường đường cho người đi bộ\n" +
                "Thao tác chuẩn bị: Sau khi hoàn thành bài thi Xuất phát, thí sinh sẽ chuyển đến bài thi thứ hai với nội dung \"Dừng xe và nhường đường cho người đi bộ\" sao cho: Dừng xe đúng chỗ trước trước vạch trắng và vạch sọc ngựa vằn (là đường dành cho người đi bộ). Khoảng cách từ thanh cản phía trước đến vạch dừng không quá 500mm.\n" +
                "\n" +
                "Bài thi Dừng xe và nhường đường cho người đi bộ.\n" +
                "\n" +
                "Các yêu cầu bài thi:\n" +
                "\n" +
                "Dừng xe ở khách cách dưới 500mm từ thanh cản phía trước ô tô đến vạch dừng\n" +
                "Giữ động cơ chạy liên tục, không để chết máy\n" +
                "Lái xe đúng theo quy định giao thông đường bộ với tốc độ di chuyển không được quá 24 km/h đối với hạng B,D và không quá 20km/h đối với hạng C,E\n" +
                "Những lỗi bị trừ điểm:\n" +
                "\n" +
                "Trừ 5 điểm: Khi không dừng ở vạch dừng đúng theo quy định\n" +
                "Trừ 5 điểm: Khi dừng xe ở khoảng cách từ thanh cản phía trước ô tô đến vạch dừng lớn hơn 500mm\n" +
                "Trừ 5 điểm: Khi dừng xe quá vạch dừng quy định\n" +
                "Trừ 5 điểm: Khi xe không hoạt động liên tục, để xe bị chết máy, mỗi lần bị trừ 5 điểm\n" +
                "Trừ 5 điểm: Khi để tốc độ động cơ vượt quá 4.000 vòng/phút, mỗi lần bị trừ 5 điểm\n" +
                "Trừ 1 điểm: Khi lái xe quá tốc độ cho phép theo quy định luật giao thông đường bộ, cứ 3 giây sẽ bị trừ 1 điểm\n" +
                "Trừ 1 điểm: Khi tổng số thời gian thực hiện bài thi sát hạch quá quy định, cứ 3 giây sẽ bị trừ đi 1 điểm\n" +
                "Người thi sẽ bị truất quyền sát hạch nếu phạm các lỗi sau đây: Quá 30 giây mà xe của bạn vẫn chưa xuất phát; Lái xe không đúng, lên vỉa hè; Gây tai nạn khi thực hành; Dưới 80 điểm sát hạch lái xe ô tô.\n"
                ;
    }

    private String getLyThuyetTextIII() {
        return "Bài thi số 3: Đi qua đường có vạch cản\n" +
                "Hướng dẫn cách thi:\n" +
                "\n" +
                "- Phải cho bánh đè qua vạch vàng\n" +
                "- Đi theo hình mũi tên\n" +
                "- Tránh các vạch cản trắng (tốc độ <20 km/h)\n" +
                "- Tiến vào bài thi tiếp theo\n" +
                "\n" +
                "Kích thước:\n" +
                "\n" +
                "- Rộng: 5m\n" +
                "- Dài: 18m\n" +
                "- Khoảng cách giữa các vạch cản: 4,5m\n" +
                "\n" +
                "Quy định xử lý lỗi:\n" +
                "\n" +
                "- Chạm vạch một lần trừ 05 điểm\n" +
                "- Chống chân 01 lần trừ 05 điểm\n" +
                "- Xe chết máy mỗi lần trừ 05 điểm\n" +
                "- Hai bánh ra khỏi hình thi trừ 25 điểm (trượt luôn)\n";
    }


    private String getLyThuyetTextIV() {
        return "Bài thi số 4: Đi qua đường gồ ghề và kết thúc\n" +
                "\n" +
                "Hướng dẫn cách thi:\n" +
                "\n" +
                "- Phải cho bánh đè qua vạch vàng\n" +
                "- Giữ đều ga, thẳng lái theo hình mũi tên\n" +
                "- Tiến xe qua vạch kết thúc\n" +
                "\n" +
                "Lưu ý: Đi theo đường gồ ghề nên cần giữ tay lái chắc chắn, nếu thấy xe yếu cần thêm chút ga để xe vượt qua các vạch cản. Khi đi qua vạch kết thúc, nếu trên loa thông báo: “XE SỐ X THI ĐẠT” thì xin chúc mừng, bạn đã hoàn thành phần thi thực hành lái xe mô tô A1.\n" +
                "\n" +
                "Kích thước:\n" +
                "- Rộng: 0,9m\n" +
                "- Dài: 15m\n" +
                "- Khoảng cách giữa các dải gồ ghề: 1,5m\n" +
                "- Chiều cao của gồ ghề: 0,05m\n" +
                "\n" +
                "Các lỗi bị trừ điểm:\n" +
                "- Chạm vạch một lần: trừ 05 điểm\n" +
                "- Chống chân một lần: trừ 05 điểm\n" +
                "- Xe chết máy mỗi lần: trừ 05 điểm\n" +
                "- Hai bánh ra khỏi hình thi: trừ 25 điểm (trượt luôn)\n" +
                "- Không hoàn thành bài thi: trừ 25 điểm (trượt luôn)\n";
    }

}