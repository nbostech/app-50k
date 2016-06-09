package com.wavelabs.fundr;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.wavelabs.fundr.model.Event;
import com.wavelabs.fundr.model.User;
import com.wavelabs.fundr.util.AppConstants;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        EventFragment.OnEventListFragmentInteractionListener,
        InvestorFragment.OnInvestorListFragmentInteractionListener,
        LandingFragment.OnFragmentInteractionListener {

    private ImageView investorImageViewBtn, startUpImageViewBtn;
    private ActionBar mActionBar;
    private ActionBarDrawerToggle toggle;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mActionBar = getSupportActionBar();
        getSupportActionBar().setTitle("");


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment landingFragment = LandingFragment.newInstance("", "");
        fragmentTransaction.replace(R.id.container, landingFragment, "landingFragment");
        fragmentTransaction.addToBackStack("landingFragment");
        fragmentTransaction.commit();


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (getFragmentManager().getBackStackEntryCount() == 1)
                super.onBackPressed();
            else
                getFragmentManager().popBackStack();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_login) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (id == R.id.nav_50kinvestors) {
            Fragment investorFragment = InvestorFragment.newInstance(3);
            fragmentTransaction.replace(R.id.container, investorFragment, "investorFragment");
            fragmentTransaction.addToBackStack("investorFragment");
            fragmentTransaction.commit();

        } else if (id == R.id.nav_50kevents) {
            // Handle the camera action

            Fragment eventFragment = EventFragment.newInstance(1);
            fragmentTransaction.replace(R.id.container, eventFragment, "eventFragment");
            fragmentTransaction.addToBackStack("eventFragment");
            fragmentTransaction.commit();

        } else if (id == R.id.nav_portfolio) {

        } else if (id == R.id.nav_aboutus) {
            Fragment about50kFragment = new About50kFragment();
            fragmentTransaction.replace(R.id.container, about50kFragment, "about50kFragment");
            fragmentTransaction.addToBackStack("about50kFragment");
            fragmentTransaction.commit();
        } else if (id == R.id.nav_blog) {

            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            intent.setData(Uri.parse(AppConstants.blogURL));
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void onEventListFragmentInteraction(Event item) {
        Log.d("test", item.name);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment eventDetailFragment = EventDetailFragment.newInstance(item, "");
        fragmentTransaction.replace(R.id.container, eventDetailFragment, "eventDetailFragment");
        fragmentTransaction.addToBackStack("eventDetailFragment");
        fragmentTransaction.commit();


    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        Log.d("Fragment URI", uri.toString());

    }

    @Override
    public void onInvestorListFragmentInteraction(User item) {
        Log.d("test", item.getProfile().getFullName());

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment investorDetailFragment = InvestorDetailFragment.newInstance(item, "");
        fragmentTransaction.replace(R.id.container, investorDetailFragment, "investorDetailFragment");
        fragmentTransaction.addToBackStack("investorDetailFragment");
        fragmentTransaction.commit();

    }

    public void resetActionBar(boolean childAction, int drawerMode) {
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
