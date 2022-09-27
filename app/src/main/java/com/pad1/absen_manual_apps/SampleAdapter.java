package com.pad1.absen_manual_apps;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class SampleAdapter extends FragmentStateAdapter {
    public SampleAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(position == 0){
            loginFragment fragment1 = new loginFragment();
            return fragment1;
        }
        else{
            signUpFragment fragment2 = new signUpFragment();
            return fragment2;
        }

    }

    @Override
    public int getItemCount() {
        return 2;
    }
}