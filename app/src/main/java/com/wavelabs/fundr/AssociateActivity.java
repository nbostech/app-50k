package com.wavelabs.fundr;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.wavelabs.fundr.model.Associate;

public class AssociateActivity extends AppCompatActivity {

    Associate associate;
    Long companyId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_associate);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        associate = (Associate) getIntent().getSerializableExtra("selectedAssociate");
        companyId = getIntent().getLongExtra("selectedCompanyId", 0L);


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment viewAssociateDetailFragment = ViewAssociateDetailFragment.newInstance(associate);
        fragmentTransaction.replace(R.id.container, viewAssociateDetailFragment, "viewAssociateDetailFragment");
        fragmentTransaction.addToBackStack("viewAssociateDetailFragment");
        fragmentTransaction.commit();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.editAssociateBtn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(AssociateActivity.this, EditAssociateActivity.class);
                intent.putExtra("selectedAssociate", associate);
                intent.putExtra("selectedCompanyId", companyId);
                startActivity(intent);
            }
        });

    }


}
