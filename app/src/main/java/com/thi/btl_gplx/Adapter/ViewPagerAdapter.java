package com.thi.btl_gplx.Adapter;



import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.thi.btl_gplx.Fragment.AFragment;
import com.thi.btl_gplx.Fragment.CFragment;
import com.thi.btl_gplx.Fragment.DFragment;
import com.thi.btl_gplx.Fragment.EFragment;
import com.thi.btl_gplx.Fragment.FFragment;
import com.thi.btl_gplx.Fragment.GFragment;


public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new AFragment();
            case 1:
                return new CFragment();
            case 2:
                return new DFragment();
            case 3:
                return new EFragment();
            case 4:
                return new FFragment();
            case 5:
                return new GFragment();
            default:
                return new AFragment();
        }
    }

    @Override
    public int getCount() {
        return 6;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position){
            case 0:
                title = "BIỂN BÁO CẤM";
                break;
            case 1:
                title = "BIỂN BÁO CHỈ DẪN";
                break;
            case 2:
                title = "BIỂN BÁO HIỆU LỆNH";
                break;
            case 3:
                title = "BIỂN BÁO PHỤ";
                break;
            case 4:
                title = "VẠCH KẺ ĐƯỜNG";
                break;
            case 5:
                title = "BIỂN BÁO TRÊN ĐƯỜNG CAO TỐC";
                break;
        }
        return title;
    }
}
