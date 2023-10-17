package com.example.myapplication.di
import com.example.myapplication.data.repository.UserRepositoryImpl
import com.example.myapplication.data.storage.UserStorage
import com.example.myapplication.data.storage.sharedprefs.SharedPrefUserStorage
import com.example.myapplication.domain.repository.UserRepository
import org.koin.dsl.module


val dataModule = module{
    single<UserStorage>{
        SharedPrefUserStorage(context = get())
    }
    single<UserRepository>{
        UserRepositoryImpl(userStorage = get())
    }

}
