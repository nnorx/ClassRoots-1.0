package com.classroots.classroots.Home;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.classroots.classroots.R;
import com.classroots.classroots.User.AccountSettingsActivity;
import com.classroots.classroots.User.UserActivity;
import com.classroots.classroots.Utils.FirebaseMethods;
import com.classroots.classroots.Utils.SectionsStatePagerAdapter;
import com.classroots.classroots.Utils.UniversalImageLoader;
import com.classroots.classroots.models.ForumListItem;
import com.classroots.classroots.models.Root;
import com.classroots.classroots.models.Thread;
import com.classroots.classroots.models.User;
import com.classroots.classroots.models.UserAccountSettings;
import com.classroots.classroots.models.UserSettings;
import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import com.classroots.classroots.Utils.BottomNavigationViewHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Nick on 12/3/2017.
 */

public class HomeFragment extends Fragment {
    private static final String TAG = "HomeActivity";
    private static final int ACTIVITY_NUM = 0;
    private Context mContext;

    private String currentRootID;

    private SectionsStatePagerAdapter pagerAdapter;
    private ViewPager mViewPager;
    private RelativeLayout mRelativeLayout;
    private BottomNavigationViewEx bottomNavigationView;
    //private ListAdapter customListAdapter;
    private Toolbar toolbar;
    private ImageView newThread;


    //////////////


    //firebase
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;
    private FirebaseMethods mFirebaseMethods;


    private TextView mRoot;
    private ProgressBar mProgressBar;
    private ListView listView;
    private CircleImageView mProfilePhoto;
    private UserAccountSettings mSettings = new UserAccountSettings();


    //vars
    private ArrayList<Root> roots = new ArrayList<>();
    //private ArrayList<UserAccountSettings> rootInfo = new ArrayList<>();




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mRoot = (TextView) view.findViewById(R.id.root_name);
        mProfilePhoto = (CircleImageView) view.findViewById(R.id.profile_photo);
        mProgressBar = (ProgressBar) view.findViewById(R.id.homeProgressBar);
        toolbar = (Toolbar) view.findViewById(R.id.HomeToolBar);
        //newThread = (ImageView) view.findViewById(R.id.new_thread);
        listView = (ListView) view.findViewById(R.id.lvHome);
        bottomNavigationView = (BottomNavigationViewEx) view.findViewById(R.id.bottomNavViewBar);
        mContext = getActivity();
        mFirebaseMethods = new FirebaseMethods(getActivity());
        Log.d(TAG, "onCreate: started.");

        //setupToolbar();
        setupBottomNavigationView();
        setupFirebaseAuth();

        mProgressBar.setVisibility(View.GONE);


        return view;

    }


    /**
     * BottomNavigationView setup
     */
    private void setupBottomNavigationView(){
        Log.d(TAG, "setupBottomNavigationView: setting up BottomNavigationView");
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationView);
        BottomNavigationViewHelper.enableNavigation(mContext, bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }


    private void setupHomeWidgets(UserSettings userSettings){
        Log.d(TAG, "setProfileWidgets: setting widgets with data retrieving from firebase database: " + userSettings.toString());
        Log.d(TAG, "setProfileWidgets: setting widgets with data retrieving from firebase database: " + userSettings.getSettings().getUsername());

        UserAccountSettings settings = userSettings.getSettings();

        if(mAuth.getCurrentUser() != null){
            Log.d(TAG, "setupForumWidgets: printing userSettings.." + userSettings);
            mSettings = settings;
            //mRoot.setText(settings.getCurrent_root());
            //getRoots(mSettings);

        }

    }


    /*
    private void getRoots(UserAccountSettings settings){
        Log.d(TAG, "setupListView. getting roots...");
        roots.clear();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        Query query = reference
                .child(getString(R.string.dbname_user_roots))
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                int i=0;
                for ( DataSnapshot singleSnapshot :  dataSnapshot.getChildren()){
                    roots.add(singleSnapshot.getValue(Root.class));

                    //Log.d(TAG, "onDataChange: Printing UIDs associated with posts.." + roots.get(i).getPoster());
                    i++;
                }

                Collections.reverse(roots);

                getThreadUserData(roots);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "onCancelled: query cancelled.");
            }
        });
    }


    */

/*
    private void getThreadUserData(final ArrayList<Root> theRoots){
        Log.d(TAG, "getThreadUserData. getting thread user data... index: ");
        //rootInfo.clear();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        Query query = reference
                .child(getString(R.string.dbname_user_account_settings))
                .orderByChild(getString(R.string.field_user_id));

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(int i=0; i<theThreads.size(); i++){
                    threadUserSettings.add(dataSnapshot.child(theThreads.get(i).getPoster()).getValue(UserAccountSettings.class));
                }
                setupListView();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "onCancelled: query cancelled.");
            }
        });
        Log.d(TAG, "getThreadUserData: added listener for query");
    }

*/


/*
    private void setupListView(){
        if(roots == null){
            Log.d(TAG, "setupListView: roots is null");
        } else {
            Log.d(TAG, "setupListView: roots are not null");
            Log.d(TAG, "setupListView: roots.size = " +roots.size());

            ArrayList<ForumListItem> forumListItems = new ArrayList<>();
            for(int i=0; i<roots.size(); i++){
                forumListItems.add(new ForumListItem(roots.get(i)));
            }

            customListAdapter = new HomeFragment.ListAdapter(mContext, R.layout.list_item_thread, forumListItems);
            listView.setAdapter(customListAdapter);
        }
    }

*/

/*
    public class ListAdapter extends ArrayAdapter<ForumListItem> {

        public ListAdapter(Context context, int textViewResourceId) {
            super(context, textViewResourceId);
        }

        public ListAdapter(Context context, int resource, List<ForumListItem> items) {
            super(context, resource, items);

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View v = convertView;

            if (v == null) {
                LayoutInflater vi;
                vi = LayoutInflater.from(getContext());
                v = vi.inflate(R.layout.list_item_thread, null);
            }

            ForumListItem p = getItem(position);

            if (p != null) {
                TextView tt1 = (TextView) v.findViewById(R.id.thread_title);
                TextView tt2 = (TextView) v.findViewById(R.id.thread_subtitle);
                TextView tt3 = (TextView) v.findViewById(R.id.poster);
                TextView tt4 = (TextView) v.findViewById(R.id.date);
                CircleImageView c1 = (CircleImageView) v.findViewById(R.id.profile_photo);

                if (tt1 != null) { tt1.setText(p.getThread().getTitle()); }
                if (tt2 != null) { tt2.setText(p.getThread().getSubtitle()); }
                if (tt3 != null) { tt3.setText(p.getSettings().getDisplay_name()); }
                if (tt4 != null) { tt4.setText(getTimestampDifference(p.getThread().getDate())); }
                if (c1 != null) { UniversalImageLoader.setImage( p.getSettings().getProfile_photo(), c1, null, "");
                }
            }
            return v;
        }

    }
*/


    /**
     * Responsible for setting up the profile toolbar
     */

    /*
    private void setupToolbar(){

        ((HomeActivity)getActivity()).setSupportActionBar(toolbar);

        newThread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: navigating to new thread activity.");
                Intent intent = new Intent(mContext, NewThreadActivity.class);
                startActivity(intent);
            }
        });

    }
    */
/*

    private String getTimestampDifference(String date){
        Log.d(TAG, "getTimestampDifference: getting timestamp difference.");

        String difference = "";
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
        sdf.setTimeZone(TimeZone.getTimeZone("US/Eastern"));//google 'android list of timezones'
        Date today = c.getTime();
        sdf.format(today);
        Date timestamp;
        final String postTimestamp = date;
        try{
            timestamp = sdf.parse(postTimestamp);
            difference = String.valueOf(Math.round(((today.getTime() - timestamp.getTime()) / 1000 / 60 / 60 / 24 )));
        }catch (ParseException e){
            Log.e(TAG, "getTimestampDifference: ParseException: " + e.getMessage() );
            difference = "0";
        }

        if(Integer.parseInt(difference)>6){
            return postTimestamp;
        } else if (Integer.parseInt(difference) < 1) {
            try{
                timestamp = sdf.parse(postTimestamp);
                difference = String.valueOf(Math.round(((today.getTime() - timestamp.getTime()) / 1000 / 60 / 60)));
            }catch (ParseException e){
                Log.e(TAG, "getTimestampDifference: ParseException: " + e.getMessage() );
                difference = "0";
            }
            if(Integer.parseInt(difference) < 1){
                try{
                    timestamp = sdf.parse(postTimestamp);
                    difference = String.valueOf(Math.round(((today.getTime() - timestamp.getTime()) / 1000 / 60 )));
                }catch (ParseException e){
                    Log.e(TAG, "getTimestampDifference: ParseException: " + e.getMessage() );
                    difference = "0";
                }
                if(difference == "1"){
                    return difference + " minute ago";
                } else {
                    return difference + " minutes ago";
                }
            } else {
                if(difference == "1"){
                    return difference + " hour ago";
                } else {
                    return difference + " hours ago";
                }
            }
        }
        if(difference == "1"){
            return difference + " day ago";
        } else {
            return difference + " days ago";
        }
    }


*/



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
                setupHomeWidgets(mFirebaseMethods.getUserSettings(dataSnapshot));

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




//
//package com.classroots.classroots.Home;
//
////import android.app.Fragment;
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.classroots.classroots.R;
//
///**
// * Created by Nick on 12/3/2017.
// */
//
//public class HomeFragment extends Fragment {
//    private static final String TAG = "HomeFragment";
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_home, container, false);
//
//        return view;
//
//
//
//    }
//}
