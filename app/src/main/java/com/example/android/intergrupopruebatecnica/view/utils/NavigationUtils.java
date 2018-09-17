package com.example.android.intergrupopruebatecnica.view.utils;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class NavigationUtils {
    public static final void navigateToBackFragment(FragmentManager fm) {
        fm.popBackStack();
    }


    public static final void changeRootFragment(int idViewToChange,
                                                FragmentManager fm,
                                                Fragment nextFragment) {

        //clears backstack to initiate new route
        fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);


        final FragmentTransaction ft = fm.beginTransaction();
        ft.replace(idViewToChange, nextFragment);
        ft.commit();

    }

    public static final void changeFragment(int idViewToChange,
                                            FragmentManager fm,
                                            Fragment nextFragment) {

        final int fragmentIndexInBackStack = findFragmentIndexInBackStack(fm,
                nextFragment.getClass().getName());

        if (fragmentIndexInBackStack > 0) {
            fm.popBackStack(fragmentIndexInBackStack,
                    FragmentManager.POP_BACK_STACK_INCLUSIVE);
        } else {
            final FragmentTransaction ft = fm.beginTransaction();
            ft.replace(idViewToChange, nextFragment);
            ft.addToBackStack(nextFragment.getClass().getName());
            ft.commit();
        }
    }


    public static final void changeFragment(int idViewToChange,
                                            FragmentManager fm,
                                            Fragment nextFragment,
                                            Bundle bundle) {
        if (bundle != null) {
            nextFragment.setArguments(bundle);
        }

        final int fragmentIndexInBackStack = findFragmentIndexInBackStack(fm,
                nextFragment.getClass().getName());

        if (fragmentIndexInBackStack > 0) {
            fm.popBackStack(fragmentIndexInBackStack,
                    FragmentManager.POP_BACK_STACK_INCLUSIVE);
        } else {
            final FragmentTransaction ft = fm.beginTransaction();
            ft.replace(idViewToChange, nextFragment);
            ft.addToBackStack(nextFragment.getClass().getName());
            ft.commit();
        }
    }

    private static final int findFragmentIndexInBackStack(FragmentManager fm,
                                                          String tagname) {
        for (int i = 0; i < fm.getBackStackEntryCount(); i++) {

            final String name = fm.getBackStackEntryAt(i).getName();
            if (name.equalsIgnoreCase(tagname)) {
                return i + 1;
            }
        }
        return 0;
    }
}
