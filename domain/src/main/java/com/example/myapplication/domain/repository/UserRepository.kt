package com.example.myapplication.domain.repository

import com.example.myapplication.domain.models.SaveUserNameParam
import com.example.myapplication.domain.models.UserName

interface UserRepository {

        fun saveName(saveParam: SaveUserNameParam): Boolean

        fun getName(): UserName
}