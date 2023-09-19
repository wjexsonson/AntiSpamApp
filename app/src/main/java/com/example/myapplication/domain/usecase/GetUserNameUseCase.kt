package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.models.UserName
import com.example.myapplication.domain.repository.UserRepository

class GetUserNameUseCase (private val userRepository: UserRepository){
    fun execute(): UserName{

        return userRepository.getName()
    }
}