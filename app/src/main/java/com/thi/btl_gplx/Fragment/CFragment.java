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

public class CFragment extends Fragment {

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
        View view = inflater.inflate(R.layout.fragment_c, container, false);
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
        data.add(new TrafficSign("I.401","Bắt đầu đường ưu tiên", "Biển chỉ dẫn các phương tiện trên trục đường chính được ưu tiên đi trước ở nơi đường giao nhau, các phương tiện từ đường nhánh phải dừng lại nhường đường cho phương tiện trên đường chính đi trước, trừ các xe được ưu tiên theo Luật Giao thông đường bộ.", R.drawable.img_75));
        data.add(new TrafficSign("I.402","Hết đường ưu tiên", "Biến chỉ dẫn đã hết đường ưu tiên. Trên đoạn đường tiếp theo, các xe đi đúng tốc độ quy định, qua nơi giao nhau ưu tiên bên phải.", R.drawable.img_76));
        data.add(new TrafficSign("I.405a I.405b I.405c","Đường cụt", "Biển báo đường cấm tất cả các loại xe cơ giới kể cả mô tô 3 bánh có thùng đi qua, trừ xe mô tô 2 bánh, xe gắn máy (kể cả xe máy điện) và các xe được ưu tiên theo Luật Giao thông đường bộ.", R.drawable.img_77));
        data.add(new TrafficSign("I.406","Được ưu tiên qua đường hẹp", "Biển này để chỉ dẫn cho người tham gia giao thông cơ giới biết mình được quyền ưu tiên đi trước trên đoạn đường hẹp.", R.drawable.img_78));
        data.add(new TrafficSign("I.407a I.407b I.407c","Đường một chiều", " Biển số I.407 (a,b,c) chỉ cho phép các loại phương tiện giao thông đi theo chiều vào theo mũi tên chỉ, cấm quay đầu ngược lại (trừ các xe được quyền ưu tiên theo quy định).", R.drawable.img_79));
        data.add(new TrafficSign("I.408","Nơi đỗ xe", "Để chỉ dẫn những nơi được phép đỗ xe, những bãi đỗ xe, bến xe, v.v...", R.drawable.img_80));
        data.add(new TrafficSign("I.408a","Nơi đỗ xe một phần trên hè phố", "Để chỉ dẫn những nơi được phép đỗ xe một phần trên hè phố rộng, đặt biển số I.408a \"Nơi đỗ xe một phần trên hè phố\". Xe phải đỗ sao cho các bánh phía ghế phụ trên hè phố.", R.drawable.img_81));
        data.add(new TrafficSign("I.409","Chỗ quay xe", "Để chỉ dẫn vị trí được phép quay đầu xe, đặt biển số I.409 \"Chỗ quay xe\".", R.drawable.img_82));
        data.add(new TrafficSign("I.410","Khu vực quay xe", "Để chỉ dẫn khu vực được phép quay đầu xe, đặt biển số I.410 \"Khu vực quay xe\". Trên biển mô tả cách thức tiến hành quay xe.", R.drawable.img_83));

        return data;
    }
}