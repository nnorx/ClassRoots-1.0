package com.classroots.classroots.Forum;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.classroots.classroots.R;
import com.classroots.classroots.User.EditProfileFragment;
import com.classroots.classroots.User.SignOutFragment;
import com.classroots.classroots.Utils.BottomNavigationViewHelper;
import com.classroots.classroots.Utils.FirebaseMethods;
import com.classroots.classroots.Utils.SectionsStatePagerAdapter;
import com.classroots.classroots.Utils.UniversalImageLoader;
import com.classroots.classroots.dialogs.ConfirmPasswordDialog;
import com.classroots.classroots.models.Thread;
import com.classroots.classroots.models.UserAccountSettings;
import com.classroots.classroots.models.UserSettings;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import de.hdodenhof.circleimageview.CircleImageView;


public class ThreadActivity extends AppCompatActivity {


    private static final String TAG = "NewThreadActivity";
    private static final int ACTIVITY_NUM = 1;


    private Context mContext;
    private EditText mTitle, mSubtitle;
    private TextView mRootName;
    private CircleImageView mProfilePhoto;

    //firebase
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;
    private FirebaseMethods mFirebaseMethods;
    private UserAccountSettings mSettings = new UserAccountSettings();
    private String userID;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newthread);
        mContext = this;
        //mRootName = (TextView) this.findViewById((R.id.root_name));
        //mProfilePhoto = (CircleImageView) this.findViewById(R.id.profile_photo);
        mFirebaseMethods = new FirebaseMethods(mContext);

        Log.d(TAG, "onCreate: started.");

        setupFirebaseAuth();


        setupBottomNavigationView();
        //setup the backarrow for back to ForumActivity
        ImageView backarrow = (ImageView) findViewById(R.id.backArrow);
        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: navigating back to ForumActivity.");
                finish();
            }
        });


        /*
        ImageView send = (ImageView) findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: attempting to post thread.");
                postThread();
                finish();
            }
        });
        */


    }



    /*
    private void postThread(){
        final String title = mTitle.getText().toString();
        final String subtitle = mSubtitle.getText().toString();

        Log.d(TAG, "postThread: title: " + title + " subtitle: " + subtitle);


        if(title != null){
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
            Query query = reference
                    .child(getString(R.string.dbname_user_account_settings))
                    .orderByChild(getString(R.string.field_user_id))
                    .equalTo(mAuth.getUid());

            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for ( DataSnapshot singleSnapshot :  dataSnapshot.getChildren()){
                        mUserAccountSettings = singleSnapshot.getValue(UserAccountSettings.class);
                    }
                    Log.d(TAG, "onDataChange: sending data to firebase methods addnewthread");
                    mFirebaseMethods.addNewThread(title, subtitle, mUserAccountSettings);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.d(TAG, "onCancelled: query cancelled.");
                }
            });
        }
    }
    */




    private void setThreadWidgets(UserSettings userSettings){

        Log.d(TAG, "setThreadWidgets: setting widgets with data retrieving from firebase database");

        UserAccountSettings settings = userSettings.getSettings();
        mSettings = userSettings.getSettings();

        if(mAuth.getCurrentUser() != null){
            mSettings = settings;



            //mRoot.setText(settings.getCurrent_root());
            //getThreadData(mSettings);





        }

    }



    /**
     * BottomNavigationView setup
     */
    private void setupBottomNavigationView() {
        Log.d(TAG, "setupBottomNavigationView: setting up BottomNavigationView");
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(mContext, bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }

    /*
    ------------------------------------ Firebase ---------------------------------------------
     */

    /**
     * Setup the firebase auth object
     */
    private void setupFirebaseAuth(){
        Log.d(TAG, "setupFirebaseAuth: setting up firebase auth.");

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        userID = mAuth.getCurrentUser().getUid();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();


                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //retrieve user information from the database
                setThreadWidgets(mFirebaseMethods.getUserSettings(dataSnapshot));

                //retrieve images for the user in question

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}