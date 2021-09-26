package com.sahil.myassigment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class FragmentAdapter extends FragmentPagerAdapter {


    public FragmentAdapter(@NonNull  FragmentManager fm) {
        super(fm);
    }



    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:return new SigninFragment();
////            case 1:return new SignupFragment();
//
////            default:return null;
            default:return new SigninFragment();
        }

    }

    @Override
    public int getCount() {
        return 1;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title=null;
        if (position==0){
            title="Sign In";
        }else if (position==1) {
            title = "Sign Up";
        }

        return title;
    }
}
