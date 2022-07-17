package com.example.divarknockoff;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class UiUtil {
    public static void changeFragment(FragmentManager fm, Fragment fragment) {
        fm.beginTransaction()
                .replace(R.id.main_frame_layout,fragment)
                .addToBackStack(fragment.getClass().getName())
                .commit();
    }
}
