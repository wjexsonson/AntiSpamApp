package com.example.myapplication.data.storage

import com.example.myapplication.data.storage.models.User

interface UserStorage {
    fun save(user: User): Boolean

    fun get(): User
}