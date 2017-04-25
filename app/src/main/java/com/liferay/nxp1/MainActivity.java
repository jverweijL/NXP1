package com.liferay.nxp1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.liferay.mobile.android.service.Session;
import com.liferay.mobile.screens.push.PushScreensActivity;
import org.json.JSONObject;

public class MainActivity extends PushScreensActivity {

    private TextView mTextMessage;
    private View content;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
                case R.id.navigation_idea:
                    Intent intent = new Intent(getApplicationContext(), IdeaActivity.class);
                    startActivity(intent);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        content = findViewById(android.R.id.content);
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
}
