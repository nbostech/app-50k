package com.app50knetwork;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.app50knetwork.model.Associate;

public class AssociateActivity extends AppCompatActivity {

    Associate associate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_associate);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        associate = (Associate) getIntent().getSerializableExtra("selectedAssociate");


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment viewAssociateDetailFragment = ViewAssociateDetailFragment.newInstance(associate);
        fragmentTransaction.replace(R.id.container, viewAssociateDetailFragment, "viewAssociateDetailFragment");
        fragmentTransaction.addToBackStack("viewAssociateDetailFragment");
        fragmentTransaction.commit();

    }


}
