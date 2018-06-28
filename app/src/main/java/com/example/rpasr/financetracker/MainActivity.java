package com.example.rpasr.financetracker;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.loopj.android.http.AsyncHttpClient;

public class MainActivity extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener {


    // UI References

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;




//  Http handler
    AsyncHttpClient mClient;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Todo Set UP android ToolBar
        android.support.v7.widget.Toolbar toolbar   = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Intlisze mdrawLayout
        mDrawerLayout = findViewById(R.id.drawer_layout);
        // Intlize actionBar Toogle
        mToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // set up Navigation View

        NavigationView navigationView =(NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);






    }
        // navigation button
    @Override

    public boolean onOptionsItemSelected(MenuItem item) {
        if ( mToggle.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        if ( id == R.id.nav_camera){

            Log.d("Tracker","currency button clicked");
            updateUI(Cryptoactivity.class);

        }
        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void updateUI( Class activity){
        // updated the UI when the clicked from navigation bar

        Intent intent = new Intent(MainActivity.this,activity);
        finish();
        startActivity(intent);


    }

}
