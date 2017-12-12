package com.classroots.classroots.Forum;

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


public class ForumActivity extends AppCompatActivity{
    private static final String TAG = "ForumActivity";
    private static final int ACTIVITY_NUM = 1;
    private Context mContext = ForumActivity.this;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);
        Log.d(TAG, "onCreate: started.");

        init();

    }


    private void init(){
        Log.d(TAG, "init: inflating " + getString(R.string.profile_fragment));

        ForumFragment fragment = new ForumFragment();
        FragmentTransaction transaction = ForumActivity.this.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(getString(R.string.profile_fragment));
        transaction.commit();
    }

}