package com.example.example_kakaologinapi.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Database(entities = [User::class], exportSchema = false, version = 1)
abstract class LoginDatabase : RoomDatabase(){

    abstract fun loginDao():LoginDao
}
