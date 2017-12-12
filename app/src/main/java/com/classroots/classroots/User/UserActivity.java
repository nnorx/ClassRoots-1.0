package com.classroots.classroots.User;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.classroots.classroots.R;
import com.classroots.classroots.Utils.BottomNavigationViewHelper;
import com.classroots.classroots.Utils.UniversalImageLoader;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;

/**
 * Created by Nick on 12/3/2017.
 */

public class UserActivity extends AppCompatActivity{
    private static final String TAG = "UserActivity";
    private static final int ACTIVITY_NUM = 4;
    private Context mContext = UserActivity.this;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Log.d(TAG, "onCreate: started.");

        init();

/*      setupBottomNavigationView();
        setupToolbar();
        setupActivityWidgets();
        setProfileImage();
        setupMyRootsList();*/
    }


    private void init(){
        Log.d(TAG, "init: inflating " + getString(R.string.profile_fragment));

        UserFragment fragment = new UserFragment();
        FragmentTransaction transaction = UserActivity.this.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(getString(R.string.profile_fragment));
        transaction.commit();
    }

    /*

    private void setProfileImage(){
        Log.d(TAG, "setProfileImage: setting profile photo.");
        String imgURL = "pbs.twimg.com/profile_images/828656877965864960/D7OpwAXV_400x400.jpg";
        UniversalImageLoader.setImage(imgURL, profilePhoto, mProgressBar, "https://");
    }

    private void setupActivityWidgets(){
        mProgressBar = (ProgressBar) findViewById(R.id.profileProgressBar);
        mProgressBar.setVisibility(View.GONE);
        profilePhoto = (ImageView) findViewById(R.id.profile_photo);
    }

    private void setupToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.profileToolBar);
        setSupportActionBar(toolbar);

        ImageView profileMenu = (ImageView) findViewById(R.id.profileMenu);
        profileMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: navigating to account settings.");
                Intent intent = new Intent(mContext, AccountSettingsActivity.class);
                startActivity(intent);
            }
        });
    }

    */
/**
     * BottomNavigationView setup
     *//*

    private void setupBottomNavigationView(){
        Log.d(TAG, "setupBottomNavigationView: setting up BottomNavigationView");
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(mContext, bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }

    private void setupMyRootsList(){
        Log.d(TAG, "setupMyRootsList: Initializing 'account MyRoots' list.");
        ListView listView = (ListView) findViewById(R.id.lvUser);

        ArrayList<String> options = new ArrayList<>();
        options.add(getString(R.string.sample1));
        options.add(getString(R.string.sample2));
        options.add(getString(R.string.sample3));
        options.add(getString(R.string.sample4));

        ArrayAdapter adapter = new ArrayAdapter(mContext, android.R.layout.simple_list_item_1, options);
        listView.setAdapter(adapter);
    }

*/


}