package com.example.testforsdk;

import android.os.Bundle;

import com.example.naurtandroidsdk.data_classes.NaurtLocation;
import com.example.naurtandroidsdk.interfaces.INaurtLocationListener;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.testforsdk.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

import com.example.naurtandroidsdk.Naurt;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private Naurt naurt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }


    public void test(){
        naurt = new Naurt( "89f76fdc-eb72-4aef-9430-847399e81406-6c0ae207-df2e-49bc-a88d-291d27beebde", getApplicationContext(), this, Long.parseLong(interval));

        INaurtLocationListener naurtListener = new INaurtLocationListener() {
            @Override
            public void callback(NaurtLocation naurtLocation, long l, ArrayList<String> notices) {
                System.out.print(notices);
                System.out.print(" | LE ");
                System.out.print(location.isLocation_services_enabled());
                System.out.print(" | WE ");
                System.out.print(location.isWireless_enabled());
                System.out.print(" | ME ");
                System.out.print(location.isMobile_network_enabled());
                System.out.println();
            }
        };
        naurt.addListener(naurtListener);
        naurt.start();
        naurt.stop();
    }
    }
}