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


public class GFragment extends Fragment {

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
        View view = inflater.inflate(R.layout.fragment_f, container, false);
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
        data.add(new TrafficSign("IE.452","Bắt đầu đường cao tốc", "Chỉ dẫn bắt đầu đường cao tốc, bao gồm các thông tin về tên và ký hiệu đường, giá trị hạn chế tốc độ tối đa và tốc độ tối thiểu.", R.drawable.img_11));
        data.add(new TrafficSign("IE.453b","Kết thúc đường cao tốc", "Chỉ dẫn điểm kết thúc đường cao tốc, bao gồm các thông tin về tên và ký hiệu đường.", R.drawable.img_12));
        data.add(new TrafficSign("IE.457a","Nơi đỗ xe trong trạm dừng nghỉ", "Biển có bốn dịch vụ: xăng dầu, ăn uống, thông tin, sửa chữa xe.", R.drawable.img_13));
        data.add(new TrafficSign("IE.463a","Trạm kiểm tra trọng tải", "Chỉ dẫn khoảng cách đến trạm kiểm tra tải trọng xe cách 750 m.", R.drawable.img_14));
        data.add(new TrafficSign("IE.467b","Nhập làn", "Chỉ dẫn vị trí nhập làn xe cách 250 m.", R.drawable.img_15));
        data.add(new TrafficSign("IE.468a","Chướng ngại vật phía trước, đi theo hướng bên trái", "Cảnh báo phía trước có sự cản trở lưu thông bình thường (nằm bên trong phần đường xe chạy hay ngay sát phần đường xe chạy) và chỉ dẫn đi theo hướng bên trái.\n" +
                "\n", R.drawable.img_16));
        data.add(new TrafficSign("IE.468b","Chướng ngại vật phía trước, đi được cả 2 hướng", "Cảnh báo phía trước có sự cản trở lưu thông bình thường (nằm bên trong phần đường xe chạy hay ngay sát phần đường xe chạy) và chỉ dẫn đi được cả hai hướng." +
                "\n", R.drawable.img_17));
        data.add(new TrafficSign("IE.468c","Chướng ngại vật phía trước, đi theo hướng bên phải", "Cảnh báo phía trước có sự cản trở lưu thông bình thường (nằm bên trong phần đường xe chạy hay ngay sát phần đường xe chạy) và chỉ dẫn đi theo hướng bên phải." +
                "\n", R.drawable.img_18));

        data.add(new TrafficSign("IE.469a","Hướng rẽ phải", "Chỉ dẫn hướng rẽ để người điều khiển phương tiện chuẩn bị đổi hướng khi sắp vào đường cong nguy hiểm, có bán kính cong nhỏ.", R.drawable.img_19));
        data.add(new TrafficSign("IE.469b","Hướng rẽ trái", "Chỉ dẫn hướng rẽ để người điều khiển phương tiện chuẩn bị đổi hướng khi sắp vào đường cong nguy hiểm, có bán kính cong nhỏ.", R.drawable.img_20));
        return data;
    }
}