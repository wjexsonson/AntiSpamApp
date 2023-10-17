package com.example.myapplication.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.myapplication.R
import com.example.myapplication.data.repository.UserRepositoryImpl
import com.example.myapplication.data.storage.sharedprefs.SharedPrefUserStorage
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.domain.usecase.GetUserNameUseCase
import com.example.myapplication.domain.usecase.SaveUserNameUseCase
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private val userRepository by lazy(LazyThreadSafetyMode.NONE) {
        UserRepositoryImpl(userStorage = SharedPrefUserStorage(context = applicationContext))
    }
    private val getUserNameUseCase  by lazy(LazyThreadSafetyMode.NONE){
        GetUserNameUseCase(userRepository = userRepository)
    }
    private val saveUserNameUseCase  by lazy(LazyThreadSafetyMode.NONE){
        SaveUserNameUseCase(userRepository = userRepository)
    }
    private lateinit var binding: ActivityMainBinding

    private val vm: MainViewModel by viewModel<MainViewModel>()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.e("AAA", "Activity created")




        val dataTextView = findViewById<TextView>(R.id.dataTextView)
        val dataEditView = findViewById<EditText>(R.id.dataEditText)
        val sendButton = findViewById<Button>(R.id.sendButton)
        val receiveButton = findViewById<Button>(R.id.receiveButton)

        vm.resultLive.observe(this, Observer {
            dataTextView.text = it
        })

        sendButton.setOnClickListener{
            val text = dataEditView.text.toString()
            vm.save(text)
        }
        receiveButton.setOnClickListener {
            vm.load()
        }
        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment)

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