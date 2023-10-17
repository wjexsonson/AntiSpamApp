package com.example.myapplication.di

import com.example.myapplication.domain.usecase.GetUserNameUseCase
import com.example.myapplication.domain.usecase.SaveUserNameUseCase
import org.koin.dsl.module

val domainModule = module {
    factory<GetUserNameUseCase>{
        GetUserNameUseCase(userRepository = get())
    }
    factory<SaveUserNameUseCase>{
        SaveUserNameUseCase(userRepository = get())
    }

}
