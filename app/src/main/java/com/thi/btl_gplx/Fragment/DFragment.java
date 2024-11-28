package com.thi.btl_gplx.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



import com.thi.btl_gplx.Activity.Noi_Dung_Bien_Bao;
import com.thi.btl_gplx.Adapter.TrafficSignAdapter;
import com.thi.btl_gplx.Models.TrafficSign;
import com.thi.btl_gplx.R;


import java.util.ArrayList;
import java.util.List;

public class DFragment extends Fragment {

    private List<TrafficSign> data;
    private TrafficSignAdapter adapter;

    private RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_d, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);



        data = getData(); // Hàm lấy dữ liệu
        adapter = new TrafficSignAdapter(data, new TrafficSignAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(TrafficSign item) {
                // Chuyển sang Activity mới và gửi dữ liệu đi
                Intent intent = new Intent(getActivity(), Noi_Dung_Bien_Bao.class);

                intent.putExtra("itemData", item);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
        return view;

    }

    private List<TrafficSign> getData() {
        List<TrafficSign> data = new ArrayList<>();
        // Thêm dữ liệu vào danh sách
        data.add(new TrafficSign("R.122","Dừng lại", "Để báo các xe (cơ giới và thô sơ) dừng lại", R.drawable.img_56));
        data.add(new TrafficSign("R.301a","Các xe chỉ được đi thẳng", "Các xe chỉ được đi thẳng.", R.drawable.img_57));
        data.add(new TrafficSign("R.301b","Các xe chỉ được rẽ phải", "Các xe chỉ được rẽ phải.", R.drawable.img_58));
        data.add(new TrafficSign("R.301c","Các xe chỉ được rẽ trái", "Các xe chỉ được rẽ trái.", R.drawable.img_59));
        data.add(new TrafficSign("R.301d","Các xe chỉ được rẽ phải", "Các xe chỉ được rẽ phải.", R.drawable.img_60));
        data.add(new TrafficSign("R.301e","Các xe chỉ được rẽ trái", "Các xe chỉ được rẽ trái.", R.drawable.img_61));
        data.add(new TrafficSign("R.301f","Các xe chỉ được đi thằng và rẽ phải", "Các xe chỉ được đi thẳng và rẽ phải.", R.drawable.img_62));
        data.add(new TrafficSign("R.301g","Các xe chỉ được đi thẳng và rẽ trái", "Các xe chỉ được đi thẳng và rẽ trái.", R.drawable.img_63));
        data.add(new TrafficSign("R.301h","Các xe chỉ được rẽ trái và phải", "Các xe chỉ được rẽ trái và phải.", R.drawable.img_64));

        data.add(new TrafficSign("R.302a","Hướng đi vòng chướng ngại vật", "", R.drawable.img_65));
        data.add(new TrafficSign("R.302b","Hướng đi vòng chướng ngại vật", "", R.drawable.img_66));

        data.add(new TrafficSign("R.303","Nơi giao nhau chạy theo vòng xuyến", "Biển có hiệu lực bắt buộc các loại xe muốn chuyển hướng phải chạy vòng theo đảo an toàn theo hướng mũi tên.", R.drawable.img_67));
        data.add(new TrafficSign("R.304","Đường dành cho xe thô sơ", "Biển có hiệu lực bắt buộc các loại xe thô sơ (kể cả xe của người tàn tật) và người đi bộ phải dùng đường dành riêng này để đi và cấm các xe cơ giới kể cả xe gắn máy, các xe được ưu tiên theo quy định đi vào đường đã đặt biển này, trừ trường hợp đi cắt ngang qua nhưng phải đảm bảo tuyệt đối an toàn cho xe thô sơ và người đi bộ.", R.drawable.img_68));

        data.add(new TrafficSign("R.305","Đường dành cho người đi bộ", "Các loại xe cơ giới và thô sơ (trừ xe đạp và xe lăn dành cho người tàn tật), kể cả các xe được ưu tiên theo quy định không được phép đi vào trên đường đã đặt biển này, trừ trường hợp đi cắt ngang qua nhưng phải đảm bảo tuyệt đối an toàn cho người đi bộ.", R.drawable.img_69));
        data.add(new TrafficSign("R.306","Tăng tốc tối thiểu cho phép", "Biển có hiệu lực bắt buộc các loại xe cơ giới vận hành với tốc độ không nhỏ hơn trị số ghi trên biển trong điều kiện giao thông thuận lợi và an toàn. Các loại xe có tốc độ tối đa theo quy định của nhà sản xuất không đạt tốc độ tối thiểu đã ghi trên biển không được phép đi vào đường này..", R.drawable.img_70));
        data.add(new TrafficSign("R.307","Hết hạn chế tốc độ tối thiểu", "Đến hết đoạn đường tốc độ tối thiểu, đặt biển số R.307 \"Hết hạn chế tốc độ tối thiểu\" (hoặc đặt biển số DP.135 \"Hết tất cả các lệnh cấm\", nếu đồng thời có nhiều biển cấm khác hết tác dụng).", R.drawable.img_71));
        data.add(new TrafficSign("R.308a","Tuyến đường cầu vượt cắt qua", "Biểu thị phía trước có cầu vượt, xe có thể đi thẳng hoặc theo chỉ dẫn trên hình vẽ để rẽ trái (hay rẽ phải), đặt biển số R.308 (a,b) “Tuyến đường cầu vượt cắt ngang”.", R.drawable.img_72));
        data.add(new TrafficSign("R.308b","Tuyến đường cầu vượt cắt qua", "Biểu thị phía trước có cầu vượt, xe có thể đi thẳng hoặc theo chỉ dẫn trên hình vẽ để rẽ trái (hay rẽ phải), đặt biển số R.308 (a,b) “Tuyến đường cầu vượt cắt ngang”.", R.drawable.img_73));
        data.add(new TrafficSign("R.309","Ấn còi", "Biểu thị xe cộ đi đến vị trí đặt biển đó thì phải ấn còi.", R.drawable.img_74));

        return data;
    }
}