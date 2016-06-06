package com.wavelabs.nfund.helper;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;

/**
 * Created by ashkumar on 5/21/2016.
 */
public class UIUtil {

    public static void resetActionBar(boolean childAction, int drawerMode,
                               ActionBar mActionBar, ActionBarDrawerToggle toggle, DrawerLayout drawer)
    {
        if (childAction) {

            toggle.setDrawerIndicatorEnabled(false);
            mActionBar.setDisplayHomeAsUpEnabled(true);
        } else {
            toggle.setDrawerIndicatorEnabled(true);
            mActionBar.setDisplayShowHomeEnabled(true);
        }

        drawer.setDrawerLockMode(drawerMode);
    }
}
