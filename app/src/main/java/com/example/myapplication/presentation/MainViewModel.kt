package com.example.myapplication.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.domain.models.SaveUserNameParam
import com.example.myapplication.domain.models.UserName
import com.example.myapplication.domain.usecase.GetUserNameUseCase
import com.example.myapplication.domain.usecase.SaveUserNameUseCase

class MainViewModel(private val getUserNameUseCase: GetUserNameUseCase,
                    private val saveUserNameUseCase: SaveUserNameUseCase):
    ViewModel() {

    private val resultLiveMutable  = MutableLiveData<String>()
    val resultLive: LiveData<String> = resultLiveMutable
    init{
        Log.e("AAA", "VM created")
    }
    override fun onCleared(){
        Log.e("AAA", "VM cleared")
        super.onCleared()
    }

    fun save(text: String){
        val params = SaveUserNameParam(name = text)
        val resultData: Boolean = saveUserNameUseCase.execute(param=params)
        resultLiveMutable.value = "Save result = $resultData"

    }
    fun load(){
        val userName: UserName = getUserNameUseCase.execute()
        resultLiveMutable.value = "${userName.firstName} ${userName.lastName}"
    }

}