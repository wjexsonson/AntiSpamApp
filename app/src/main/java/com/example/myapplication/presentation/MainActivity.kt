package com.example.myapplication.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.myapplication.R
import com.example.myapplication.data.repository.UserRepositoryImpl
import com.example.myapplication.data.storage.sharedprefs.SharedPrefUserStorage
import com.example.myapplication.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private val userRepository by lazy(LazyThreadSafetyMode.NONE) {
        UserRepositoryImpl(userStorage = SharedPrefUserStorage(context = applicationContext))
    }
    private val getUserNameUseCase  by lazy(LazyThreadSafetyMode.NONE){
        com.example.myapplication.domain.usecase.GetUserNameUseCase(userRepository = userRepository)
    }
    private val saveUserNameUseCase  by lazy(LazyThreadSafetyMode.NONE){
        com.example.myapplication.domain.usecase.SaveUserNameUseCase(userRepository = userRepository)
    }
    private lateinit var binding: ActivityMainBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataTextView = findViewById<TextView>(R.id.dataTextView)
        val dataEditView = findViewById<EditText>(R.id.dataEditText)
        val sendButton = findViewById<Button>(R.id.sendButton)
        val receiveButton = findViewById<Button>(R.id.receiveButton)

        sendButton.setOnClickListener{
            val text = dataEditView.text.toString()
            val params = com.example.myapplication.domain.models.SaveUserNameParam(name = text)
            val result: Boolean = saveUserNameUseCase.execute(param=params)
            dataTextView.text = "Save result = $result"
        }
        receiveButton.setOnClickListener {
            val userName: com.example.myapplication.domain.models.UserName = getUserNameUseCase.execute()
            dataTextView.text = "${userName.firstName} ${userName.lastName}"
        }
        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_calls,
                R.id.navigation_sms,
                R.id.navigation_contacts,
                R.id.navigation_cards,
                R.id.navigation_blocks
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}