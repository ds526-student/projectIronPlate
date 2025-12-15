package com.example.projectironplate

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.projectironplate.database.DBHelper
import com.example.projectironplate.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(getLayoutInflater())
        setContentView(binding!!.getRoot())

        val navView = findViewById<BottomNavigationView?>(R.id.nav_view)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val navController = findNavController(this, R.id.nav_host_fragment_activity_main)
        setupWithNavController(binding!!.navView, navController)

        val dbHelper = DBHelper(this)

        // checks for database existence and acts accordingly
        try {
            dbHelper.prepDB()
            Log.d("DB_INIT", "Database ready to use!")
        } catch (e: IOException) {
            e.printStackTrace()
            Log.e("DB_INIT", "Error preparing database: " + e.message)
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}