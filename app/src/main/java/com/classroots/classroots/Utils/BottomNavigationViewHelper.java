package com.classroots.classroots.Utils;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.MenuItem;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import com.classroots.classroots.Home.HomeActivity;
import com.classroots.classroots.Forum.ForumActivity;
import com.classroots.classroots.Calendar.CalendarActivity;
import com.classroots.classroots.Notes.NotesActivity;
import com.classroots.classroots.User.UserActivity;
import com.classroots.classroots.R;

/**
 * Created by Nick on 12/3/2017.
 */

public class BottomNavigationViewHelper {

    private static final String TAG = "BottomNavigationViewHel";

    public static void setupBottomNavigationView(BottomNavigationViewEx bottomNavigationViewEx){
        Log.d(TAG, "setupBottomNavigationView: Setting up BottomNavigationView");
        bottomNavigationViewEx.enableAnimation(false);
        bottomNavigationViewEx.enableItemShiftingMode(false);
        bottomNavigationViewEx.enableShiftingMode(false);
        bottomNavigationViewEx.setTextVisibility(false);
    }

    public static void enableNavigation(final Context context, BottomNavigationViewEx view){
        view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.ic_home:
                        Intent intent1 = new Intent(context, HomeActivity.class)
                                .addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);//ACTIVITY_NUM = 0
                        context.startActivity(intent1);
                        break;

                    case R.id.ic_chat:
                        Intent intent2  = new Intent(context, ForumActivity.class)
                                .addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);//ACTIVITY_NUM = 1
                        context.startActivity(intent2);
                        break;

                    case R.id.ic_calendar:
                        Intent intent3 = new Intent(context, CalendarActivity.class)
                                .addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);//ACTIVITY_NUM = 2
                        context.startActivity(intent3);
                        break;

                    case R.id.ic_note:
                        Intent intent4 = new Intent(context, NotesActivity.class)
                                .addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);//ACTIVITY_NUM = 3
                        context.startActivity(intent4);
                        break;

                    case R.id.ic_user:
                        Intent intent5 = new Intent(context, UserActivity.class)
                                .addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);//ACTIVITY_NUM = 4
                        context.startActivity(intent5);
                        break;
                }

                /**
                 *
                 *
                 *                 switch (item.getItemId()){

                 case R.id.ic_home:
                 Intent intent1 = new Intent(context, HomeActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);//ACTIVITY_NUM = 0
                 context.startActivity(intent1);
                 break;

                 case R.id.ic_chat:
                 Intent intent2  = new Intent(context, ForumActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);//ACTIVITY_NUM = 1
                 context.startActivity(intent2);
                 break;

                 case R.id.ic_calendar:
                 Intent intent3 = new Intent(context, CalendarActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);//ACTIVITY_NUM = 2
                 context.startActivity(intent3);
                 break;

                 case R.id.ic_note:
                 Intent intent4 = new Intent(context, NotesActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);//ACTIVITY_NUM = 3
                 context.startActivity(intent4);
                 break;

                 case R.id.ic_user:
                 Intent intent5 = new Intent(context, UserActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);//ACTIVITY_NUM = 4
                 context.startActivity(intent5);
                 break;
                 }

                 *
                 *
                 */




                return false;
            }
        });
    }
}