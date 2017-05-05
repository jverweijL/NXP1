package com.liferay.nxp1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.liferay.mobile.android.service.Session;
import com.liferay.mobile.push.Push;
import com.liferay.mobile.screens.push.PushScreensActivity;
import org.json.JSONObject;

public class MainActivity extends PushScreensActivity implements   RatingFragment.OnFragmentInteractionListener,
                                                                    IdeaFragment.OnFragmentInteractionListener,
                                                                    BlogFragment.OnFragmentInteractionListener,
                                                                    SelfserviceFragment.OnFragmentInteractionListener,
                                                                    PaySlipFragment.OnFragmentInteractionListener,
                                                                    ShiftChangeFragment.OnFragmentInteractionListener,
                                                                    NewsFragment.OnFragmentInteractionListener {
  
    private View content;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    MainFragment main = new MainFragment();
                    transaction.replace(R.id.fragment_container,main);
                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    transaction.commit();
                    return true;
                case R.id.navigation_blog:
                    BlogFragment blog = new BlogFragment();
                    transaction.replace(R.id.fragment_container,blog);
                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    transaction.commit();
                    return true;
                case R.id.navigation_rating:
                    RatingFragment rating = new RatingFragment();
                    transaction.replace(R.id.fragment_container,rating);
                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    transaction.commit();
                    return true;
                case R.id.navigation_briefcase:
                    SelfserviceFragment selfservice = new SelfserviceFragment();
                    transaction.replace(R.id.fragment_container,selfservice);
                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    transaction.commit();
                    return true;
                case R.id.navigation_idea:
                    IdeaFragment idea = new IdeaFragment();
                    transaction.replace(R.id.fragment_container,idea);
                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    transaction.commit();
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        content = findViewById(android.R.id.content);
        
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        FragmentPager main = new FragmentPager();
        transaction.replace(R.id.fragment_container,main);
        transaction.commit();
    }

    @Override
    protected Session getDefaultSession() {
        return null;
    }

    @Override
    protected void onPushNotificationReceived(JSONObject jsonObject) {
        Log.d("PUSH", "Push received"+ jsonObject.toString());
    }

    @Override
    protected void onErrorRegisteringPush(String message, Exception e) {
        Snackbar.make(content, "Error registering device for push notifications", Snackbar.LENGTH_SHORT);
    }

    @Override
    protected String getSenderId() {
        return "1035449703602";
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
    }
}
