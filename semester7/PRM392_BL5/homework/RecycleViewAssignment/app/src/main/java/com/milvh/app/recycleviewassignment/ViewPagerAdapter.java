package com.milvh.app.recycleviewassignment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.milvh.app.recycleviewassignment.Fragment.CategoryFragment;
import com.milvh.app.recycleviewassignment.Fragment.DealFragment;
import com.milvh.app.recycleviewassignment.Fragment.FeaturedFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
       switch (position){
           case 0:
               return new FeaturedFragment();
           case 1:
               return new DealFragment();
           case 2:
               return new CategoryFragment();
           default:
               return new FeaturedFragment();
       }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
