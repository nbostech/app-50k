package com.wavelabs.nfund;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.wavelabs.nfund.helper.OnTeamAssocListFragmentInteractionListener;
import com.wavelabs.nfund.model.Associate;
import com.wavelabs.nfund.model.Company;
import com.wavelabs.nfund.service.CompanyAPI;

import java.io.File;

import in.wavelabs.idn.ConnectionAPI.NBOSCallback;
import retrofit2.Response;

public class CompanyProfileActivity extends AppCompatActivity implements
        OnTeamAssocListFragmentInteractionListener,
        CreateAssociateDialogFragment.OnCreateAssociateDialogFragmentInteractionListener{

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    static Company company;


    static int RESULT_LOAD_IMAGE = 1;
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    public RelativeLayout layout;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_profile);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        layout = (RelativeLayout) findViewById(R.id.progressBar1);

        company = (Company)getIntent().getSerializableExtra("selectedCompany");

        // Set up the section adapter
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());


        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.profileTabcontainer);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // Set up the tab layout for viewpager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
                Log.d("test",tab.getPosition()+" "+mViewPager.getCurrentItem());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });




    }


    private void showEditDialog() {
        FragmentManager fm = getSupportFragmentManager();
        CreateAssociateDialogFragment editNameDialog = new CreateAssociateDialogFragment();
        editNameDialog.show(fm, "fragment_edit_name");
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            ImageView imageView = (ImageView) findViewById(R.id.companyLogoIV);
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));

            CompanyAPI.uploadMedia(CompanyProfileActivity.this,Long.toString(company.getId()),"company_logo",new File(picturePath), new NBOSCallback() {

                @Override
                public void onResponse(Response response) {
                    Snackbar.make(getWindow().getDecorView().getRootView(), "Uploaded successfully", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }

                @Override
                public void onFailure(Throwable t) {

                }


            });



        }


    }

    @Override
    public void onTeamAssocListFragmentInteraction(Associate item) {
        Intent intent = new Intent(CompanyProfileActivity.this, AssociateActivity.class);
        intent.putExtra("selectedAssociate",item);
        intent.putExtra("selectedCompanyId",company.getId());
        startActivity(intent);

        /*
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment viewAssociateDetailFragment = ViewAssociateDetailFragment.newInstance(item);
        fragmentTransaction.replace(R.id.profileTabcontainer, viewAssociateDetailFragment, "viewAssociateDetailFragment");
        fragmentTransaction.addToBackStack("viewAssociateDetailFragment");
        fragmentTransaction.commit();
        */
    }

    @Override
    public void onCreateAssociateDialogFragmentInteraction(Uri uri) {

    }




    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {



        public SectionsPagerAdapter(FragmentManager fm) {

            super(fm);
        }


        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            if (position == 0) {
                /*
                Fragment profileTabFragment = ProfileTabFragment.newInstance(position + 1);
                fragmentTransaction.replace(R.id.container, profileTabFragment, "profileTabFragment");
                fragmentTransaction.addToBackStack("profileTabFragment");
                fragmentTransaction.commit();
                */
                return ProfileTabFragment.newInstance(position + 1);
                //return profileTabFragment;
            }
            else if (position == 1)
                return FinancialTabFragment.newInstance(position + 1);
            else if (position == 2)
                return SummaryTabFragment.newInstance(position + 1);
            else if (position == 3)
                return TeamTabFragment.newInstance(3,position + 1);
            else
                return ProfileTabFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.

                return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "PROFILE";
                case 1:
                    return "FINANCIAL INFO";
                case 2:
                    return "SUMMARY";
                case 3:
                    return "TEAM";
            }
            return null;
        }
    }
}
