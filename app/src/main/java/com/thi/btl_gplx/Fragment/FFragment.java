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


public class FFragment extends Fragment {

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
        data.add(new TrafficSign("V.1.1","Vạch phân chia hai chiều xe chạy, dạng vạch đơn, đứt nét", "Dùng để phân chia hai chiều xe chạy ngược chiều nhau ở những đoạn đường có từ 2 làn xe trở lên, không có dải phân cách giữa. Xe được phép cắt qua để sử dụng làn ngược chiều từ cả hai phía.", R.drawable.img_21));
        data.add(new TrafficSign("V.1.2","Vạch phân chia hai chiều xe chạy(vạch tim đường), dạng vạch đơn, nét liền", "Dùng để phân chia hai chiều xe chạy cho đường có 2 hoặc 3 làn xe, không có dải phân cách giữa; xe không được lấn làn, không được đè lên vạch. Vạch này thường sử dụng ở đoạn đường không đảm bảo tầm nhìn vượt xe, nguy cơ tai nạn giao thông đối đầu lớn.", R.drawable.img_22));
        data.add(new TrafficSign("V.1.3","Vạch phân chia hai chiều xe chạy(vạch tim đường), dạng vạch đôi, nét liền", "Dùng để phân chia hai chiều xe chạy cho đường có từ 4 làn xe trở lên, không có dải phân cách giữa, xe không được lấn làn, không được đè lên vạch. Vạch này thường sử dụng ở đoạn đường không đảm bảo tầm nhìn vượt xe, nguy cơ tai nạn giao thông đối đầu lớn hoặc ở các vị trí cần thiết khác.", R.drawable.img_23));
        data.add(new TrafficSign("V.1.4","Vạch phân chia hai chiều xe chạy(vạch tim đường), dạng vạch đôi gồm một vạch nét liền, một vạch nét đứt", "Dùng để phân chia hai chiều xe chạy cho đường có từ 2 làn xe trở lên, không có dải phân cách hai chiều xe chạy, sử dụng ở các đoạn cần thiết phải cấm xe sử dụng làn ngược chiều theo một hướng xe chạy nhất định để đảm bảo an toàn. Xe trên làn  đường tiếp giáp với vạch đứt nét được phép cắt qua và sử dụng làn ngược chiều khi cần thiết; xe trên làn đường tiếp giáp với vạch liền nét không được cắt qua vạch.", R.drawable.img_24));
        data.add(new TrafficSign("V.1.5","Vạch xác định ranh giới làn đường có thể thay đổi hướng xe chạy", "Dùng để xác định ranh giới làn đường có thể thay đổi hướng xe chạy trên đó theo thời gian. Hướng xe chạy ở một thời điểm trên làn đường có thể đổi chiều được quy định bởi người điều khiển giao thông, tín hiệu đèn, biển báo hoặc các báo hiệu khác phù hợp.", R.drawable.img_25));
        data.add(new TrafficSign("V.2.1","Vạch phân chia các làn đường cùng chiều, dạng vạch đơn, đứt nét", "Dùng để phân chia các làn xe cùng chiều. Trong trường hợp này, xe được phép thực hiện việc chuyển làn đường qua vạch 2.1.", R.drawable.img_26));

        data.add(new TrafficSign("V.2.2","Vạch phân chia các làn xe cùng chiều, dạng vạch đơn, liền nét", "Dùng để phân chia các làn xe cùng chiều trong trường hợp không cho phép xe chuyển làn hoặc sử dụng làn khác; xe không được lấn làn, không được đè lên vạch.", R.drawable.img_27));
        data.add(new TrafficSign("V.2.3","Vạch giới hạn làn đường dành riêng hoặc làn đường ưu tiên", "Vạch giới hạn làn đường dành riêng cho một loại xe cơ giới nhất định (vạch liền nét), các loại xe khác không được đi vào làn xe này trừ những trường hợp khẩn cấp theo Luật Giao thông đường bộ.", R.drawable.img_28));
        data.add(new TrafficSign("V.2.4","Vạch phân chia các làn xe cùng chiều, dạng vạch kép(một vạch liền, một vạch nét)", "Dùng để phân chia các làn xe cùng chiều, xe trên làn đường tiếp giáp với vạch đứt nét được phép cắt qua khi cần thiết; xe trên làn đường tiếp giáp với vạch liền nét không được lấn làn hoặc đè lên vạch.", R.drawable.img_29));

        data.add(new TrafficSign("V.3.1","Vạch giới hạn mép ngoài phần đường xe chạy hoặc vạch phân cách làn xe cơ giới và làn xe thô sơ", "Để xác định mép ngoài phần đường xe chạy; hoặc phân cách làn xe cơ giới và xe thô sơ, xe chạy được phép đè lên vạch khi cần thiết và phải nhường đường cho xe thô sơ.", R.drawable.img_30));
        data.add(new TrafficSign("V.7.6","Vạch chỉ dẫn sắp đến chỗ có bố trí vạch đi bộ qua đường", "Để báo hiệu sắp đến chỗ có bố trí vạch đi bộ qua đường; đặc biệt đối với các chỗ bố trí vạch đi bộ qua đường ở giữa đoạn đường nối hai nút để cảnh báo người lái xe phải nhường đường cho người đi bộ qua đường.", R.drawable.img_31));
        data.add(new TrafficSign("V.7.8","Vạch xác định khoảng cách xe trên đường", "Dùng để xác định khoảng cách trên đường, giúp cho lái xe biết cần phải giãn cách cự ly để đảm bảo an toàn với xe chạy phía trước. Vạch thường được sử dụng trên đường ôtô cao tốc ở những nơi hay xảy ra tai nạn do vượt xe hoặc đâm va từ phía sau hoặc ở những vị trí có yêu cầu đặc biệt. Vạch được sử dụng kèm biển báo ghi khoảng cách giữa các vạch theo phương dọc đường.", R.drawable.img_32));
        data.add(new TrafficSign("V.9.2","Vạch quy định vị trí dừng đỗ của phương tiện giao thông công cộng trên đường", "Quy định vị trí dừng đỗ xe của phương tiện vận tải hành khách công cộng trên đường: Xe buýt, tác xi, vv…", R.drawable.img_33));
        data.add(new TrafficSign("V.9.3","Vạch mũi tên chỉ hướng trên mặt đường", "Vạch mũi tên chỉ hướng trên mặt đường được sử dụng để chỉ hướng xe phải đi, mũi tên chỉ hướng chủ yếu sử dụng ở các nút giao có tách nhập làn và trên đường có nhiều làn xe, mũi tên cũng có thể được sử dụng cho các phần đường xe chạy 1 chiều để xác nhận hướng giao thông.", R.drawable.img_34));
        return data;
    }
}