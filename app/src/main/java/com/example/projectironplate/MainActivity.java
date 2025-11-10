package com.example.projectironplate;

import android.os.Bundle;
import android.util.Log;

import com.example.projectironplate.database.DBHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.projectironplate.databinding.ActivityMainBinding;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_food, R.id.navigation_workouts, R.id.navigation_profile)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        DBHelper dbHelper = new DBHelper(this);

        // checks for database existence and acts accordingly
        try {
            dbHelper.prepDB();
            Log.d("DB_INIT", "Database ready to use!");

            // outputs details of a product to logcat
            dbHelper.logProductDetails(15000);
            
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("DB_INIT", "Error preparing database: " + e.getMessage());
        }
    }

}