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


public class AFragment extends Fragment {
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
        View view = inflater.inflate(R.layout.fragment_a, container, false);
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
        data.add(new TrafficSign("P.101","Đường cấm", "Biển báo đường cấm tất cả các loại phương tiện tham gia giao thông đi lại cả hai hướng, trừ xe ưu tiên theo luật định.", R.drawable.img));
        data.add(new TrafficSign("P.102","Cấm đi ngược chiều", "Biến báo đường cấm tất cả các loại phương tiện tham gia giao thông đi vào theo chiều đặt biển.", R.drawable.bienbao));
        data.add(new TrafficSign("P.103a","Cấm ô tô", "Biển báo đường cấm tất cả các loại xe cơ giới kể cả mô tô 3 bánh có thùng đi qua, trừ xe mô tô 2 bánh, xe gắn máy (kể cả xe máy điện) và các xe được ưu tiên theo Luật Giao thông đường bộ.", R.drawable.img_1));
        data.add(new TrafficSign("P.103b","Cấm ô tô rẽ phải", "Biển báo đường cấm tất cả các loại xe cơ giới kể cả môtô 3 bánh có thùng rẽ phải, trừ môtô hai bánh, xe gắn máy và các xe được ưu tiên theo quy định.", R.drawable.img_2));
        data.add(new TrafficSign("P.103c","Cấm ô tô rẽ trái", "Biến báo đường cấm tất cả các loại xe cơ giới kể cả môtô 3 bánh có thùng rẽ trái, trừ môtô hai bánh, xe gắn máy và các xe được ưu tiên theo quy định.", R.drawable.img_3));
        data.add(new TrafficSign("P.104","Cấm mô tô", "Biển báo đường cấm tất cả các loại môtô đi qua, trừ các xe môtô được ưu tiên theo quy định.", R.drawable.img_4));

        data.add(new TrafficSign("P.105","Cấm ô tô và mô tô", "Biển báo đường cấm tất cả các loại xe cơ giới và môtô đi qua trừ xe gắn máy và các xe được ưu tiên theo quy định.", R.drawable.img_5));
        data.add(new TrafficSign("P.106a","Cấm ô tô tải", "Biến báo đường cấm tất cả các loại ôtô chở hàng có trọng tải từ 1,5 tấn trở lên trừ các xe được ưu tiên theo quy định. Biển có hiệu lực cấm đối với cả máy kéo và các xe máy chuyên dùng.\n" +
                "\n", R.drawable.img_6));
        data.add(new TrafficSign("P.106b","Cấm ô tô tải theo trọng lượng", "Có tổng trọng lượng (trọng lượng xe cộng hàng) vượt quá con số đã quy định trên biển. Biển có hiệu lực cấm đối với cả máy kéo và các xe máy chuyên dùng đi vào đoạn đường đặt biển.", R.drawable.img_7));
        data.add(new TrafficSign("P.106c","Cấm xe chở hàng nguy hiểm", "Biển  báo đường cấm các xe chở hàng nguy hiểm.", R.drawable.img_8));
        data.add(new TrafficSign("P.107","Cấm ô tô khách và ô tô tải", "Biến báo đường cấm ôtô chở khách và các loại ôtô tải kể cả các loại máy kéo và xe máy thi công chuyên dùng đi qua trừ các xe được ưu tiên theo quy định.", R.drawable.img_9));
        data.add(new TrafficSign("P.108","Cấm ô tô, máy kéo kéo moóc hoặc sơ mi rơ moóc", "Biển báo đường cấm tất cả các loại xe cơ giới kéo theo rơ-moóc kể cả môtô, máy kéo, ôtô khách kéo theo rơ-moóc đi qua, trừ loại ôtô sơ-mi rơ-moóc và các xe được ưu tiên (có kéo theo rơ-moóc) theo luật lệ nhà nước quy định.", R.drawable.img_10));
        return data;
    }


}