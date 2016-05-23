package com.app50knetwork;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.app50knetwork.helper.OnTeamAssocListFragmentInteractionListener;
import com.app50knetwork.model.Associate;
import com.app50knetwork.model.Company;
import com.app50knetwork.model.Event;
import com.app50knetwork.model.User;
import com.app50knetwork.util.AppConstants;

public class StartupMainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        EventFragment.OnEventListFragmentInteractionListener,
        StartupLandingFragment.OnStartupListFragmentInteractionListener,
        InvestorFragment.OnInvestorListFragmentInteractionListener,
        CreateStartupDialogFragment.OnCreateStartupDialogFragmentInteractionListener,
        ViewStartupFragment.OnViewStartupFragmentInteractionListener,
        OnTeamAssocListFragmentInteractionListener {
    ActionBar mActionBar;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        mActionBar = getSupportActionBar();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment startupLandingFragment = StartupLandingFragment.newInstance(3);
        fragmentTransaction.replace(R.id.startUpcontainer, startupLandingFragment, "startupLandingFragment");
        fragmentTransaction.addToBackStack("startupLandingFragment");
        fragmentTransaction.commit();


        drawer = (DrawerLayout) findViewById(R.id.startup_drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.startup_nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.startup_drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.startup_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Log.d("test", id + "");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (id == R.id.nav_s50kinvestors) {
            Fragment investorFragment = InvestorFragment.newInstance(4);
            fragmentTransaction.replace(R.id.startUpcontainer, investorFragment, "investorFragment");
            fragmentTransaction.addToBackStack("investorFragment");
            fragmentTransaction.commit();

        } else if (id == R.id.nav_s50kevents) {
            // Handle the camera action

            Fragment eventFragment = EventFragment.newInstance(1);
            fragmentTransaction.replace(R.id.startUpcontainer, eventFragment, "eventFragment");
            fragmentTransaction.addToBackStack("eventFragment");
            fragmentTransaction.commit();

        } else if (id == R.id.nav_sEditProfile) {

        } else if (id == R.id.nav_sAboutus) {
            Fragment about50kFragment = About50kFragment.newInstance();
            fragmentTransaction.replace(R.id.startUpcontainer, about50kFragment, "about50kFragment");
            fragmentTransaction.addToBackStack("about50kFragment");
            fragmentTransaction.commit();
        } else if (id == R.id.nav_sBlog) {

            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            intent.setData(Uri.parse(AppConstants.blogURL));
            startActivity(intent);

        } else if (id == R.id.nav_sHome) {

        } else if (id == R.id.nav_sSettings) {

        } else if (id == R.id.nav_sMyNetwork) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.startup_drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onEventListFragmentInteraction(Event item) {
        Log.d("test", item.name);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment eventDetailFragment = EventDetailFragment.newInstance(item, "");
        fragmentTransaction.replace(R.id.startUpcontainer, eventDetailFragment, "eventDetailFragment");
        fragmentTransaction.addToBackStack("eventDetailFragment");
        fragmentTransaction.commit();


    }


    @Override
    public void onInvestorListFragmentInteraction(User item) {
        Log.d("test", item.getProfile().getFullName());

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment investorDetailFragment = InvestorDetailFragment.newInstance(item, "");
        fragmentTransaction.replace(R.id.startUpcontainer, investorDetailFragment, "investorDetailFragment");
        fragmentTransaction.addToBackStack("investorDetailFragment");
        fragmentTransaction.commit();

    }

    public void resetActionBar(boolean childAction, int drawerMode)
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


    @Override
    public void onStartupListFragmentInteraction(Company item) {
        /*
        Log.d("test",item.getProfile().getName());
        Intent intent = new Intent(StartupMainActivity.this, CompanyProfileActivity.class);
        intent.putExtra("selectedCompany",item);
        startActivity(intent);
        */
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment viewStartupFragment = ViewStartupFragment.newInstance(item, "");
        fragmentTransaction.replace(R.id.startUpcontainer, viewStartupFragment, "viewStartupFragment");
        fragmentTransaction.addToBackStack("viewStartupFragment");
        fragmentTransaction.commit();
    }

    @Override
    public void onCreateStartupDialogFragmentInteraction(Uri uri) {

    }

    @Override
    public void onViewStartupFragmentInteraction(Uri uri) {

    }

    @Override
    public void onTeamAssocListFragmentInteraction(Associate item) {

    }
}
