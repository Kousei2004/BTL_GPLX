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


public class EFragment extends Fragment {

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
        View view = inflater.inflate(R.layout.fragment_e, container, false);
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
        data.add(new TrafficSign("S.501","Phạm vi tác dụng của biển", " Để thông báo chiều dài đoạn đường nguy hiểm hoặc cấm hoặc hiệu lệnh hoặc hạn chế bên dưới một số biển báo chính) Chiều dài đoạn nguy hiểm hoặc cấm hoặc hạn chế ghi theo đơn vị mét (m) và lấy chẵn đến hàng chục mét.", R.drawable.img_35));
        data.add(new TrafficSign("S.502","Khoảng cách đến đối tượng báo hiệu", "Bên dưới các loại biển báo nguy hiểm, biển báo cấm, biển hiệu lệnh và chỉ dẫn, trong trường hợp vị trí đặt các biển báo đó khác với quy định chung, đặt biển số S.502 \"Khoảng cách đến đối tượng báo hiệu\" để thông báo khoảng cách thực tế từ vị trí đặt biển đến đối tượng báo hiệu ở phía trước.", R.drawable.img_36));
        data.add(new TrafficSign("S.503a","Hướng tác dụng của biển", "", R.drawable.img_37));
        data.add(new TrafficSign("S.503b","Hướng tác dụng của biển", "", R.drawable.img_38));
        data.add(new TrafficSign("S.503c","Hướng tác dụng của biển", "", R.drawable.img_39));
        data.add(new TrafficSign("S.503d","Hướng tác dụng của biển", "", R.drawable.img_40));
        data.add(new TrafficSign("S.503e","Hướng tác dụng của biển", "", R.drawable.img_41));
        data.add(new TrafficSign("S.503f","Hướng tác dụng của biển", "", R.drawable.img_42));
        data.add(new TrafficSign("S.504","Làn đường", "", R.drawable.img_43));
        data.add(new TrafficSign("S.506a","Hướng đường ưu tiên", "", R.drawable.img_44));
        data.add(new TrafficSign("S.506b","Hướng đường ưu tiên", "", R.drawable.img_45));
        data.add(new TrafficSign("S.507","Hướng rẽ", "", R.drawable.img_46));
        data.add(new TrafficSign("S.508a","Biểu thị thời gian", "Được đặt dưới biển báo cấm hoặc biển hiệu lệnh nhằm quy định phạm vi thời gian hiệu lực của các biển báo cấm, biển hiệu lệnh cho phù hợp yêu cầu.", R.drawable.img_47));
        data.add(new TrafficSign("S.508b","Biểu thị thời gian", "Được đặt dưới biển báo cấm hoặc biển hiệu lệnh nhằm quy định phạm vi thời gian hiệu lực của các biển báo cấm, biển hiệu lệnh cho phù hợp yêu cầu.", R.drawable.img_48));
        data.add(new TrafficSign("S.509a","Chiều cao an toàn", "chỉ rõ chiều cao cho các phương tiện đi qua an toàn.", R.drawable.img_49));
        data.add(new TrafficSign("S.510a","Chú ý đường trơn có băng tuyết", "Để cảnh báo đường trơn, có tuyết trong những ngày trời có tuyết, phải đặt biển phụ số S.510 “Chú ý đường trơn có băng tuyết”. Biển hình chữ nhật nền đỏ, chữ và viền màu trắng.", R.drawable.img_50));
        data.add(new TrafficSign("S.G,7 S.G,8","Biển chỉ dẫn tới địa điểm cắm trại và nhà trọ", "Để chỉ dẫn tới địa điểm cắm trại.", R.drawable.img_51));
        data.add(new TrafficSign("S.G,9b","Biển chỉ dẫn tới địa điểm đỗ xe", "Để chỉ dẫn tới địa điểm đỗ xe dành cho xe có lái xe muốn sử dụng phương tiện công cộng.", R.drawable.img_52));
        data.add(new TrafficSign("S.G,11a S.G,11c","Biển chỉ dẫn hướng đi cho từng làn", "Để thông báo cho lái xe số làn và hướng đi của từng làn xe, phải cắm biển số S.G,11a; S.G,11c; các biển này phải có số mũi tên bằng số lượng làn xe đi cùng hướng và phải chỉ dẫn số lượng làn xe của hướng đi sắp tới.", R.drawable.img_53));
        data.add(new TrafficSign("S.G,12a S.G,12b","Biển chỉ dẫn làn đường không lưu thông", "Để chỉ dẫn cho lái xe biết làn đường không lưu thông.", R.drawable.img_54));
        data.add(new TrafficSign("S.H,6","Biển báo ngoại lệ", "Để chỉ các trường hợp mà biển cấm hoặc hạn chế được coi là không áp dụng đặc biệt cho một nhóm đối tượng tham gia giao thông nào đó phải cắm biển S.H,6 và thể hiện nhóm đối tượng đó cùng với cụm từ “Except - Ngoại lệ”.", R.drawable.img_55));
        return data;
    }
}