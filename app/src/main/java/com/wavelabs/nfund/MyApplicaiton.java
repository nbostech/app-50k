package com.wavelabs.nfund;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;

import com.facebook.accountkit.AccountKit;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import in.wavelabs.idn.WaveLabsSdk;

/**
 * Created by ashkumar on 4/9/2016.
 */
public class MyApplicaiton extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // Initialize the SDK before executing any other operations,
        // especially, if you're using Facebook UI elements.
        WaveLabsSdk.SdkInitialize(this);
        AccountKit.initialize(this);
        WaveLabsSdk.generateKeyHash(getApplicationContext(), "com.app50knetwork");

        //generateKeyHash(getApplicationContext(),"com.app50knetwork");

    }

    public static void generateKeyHash(Context context, String packageName) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(
                    packageName,
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException | NoSuchAlgorithmException e) {

        }
    }

}
