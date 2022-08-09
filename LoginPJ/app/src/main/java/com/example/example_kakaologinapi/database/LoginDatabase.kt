package com.example.example_kakaologinapi.database

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], exportSchema = false, version = 1)
abstract class LoginDatabase : RoomDatabase(){
    abstract val loginDao : LoginDao

    companion object{
        private var LoginInstance : LoginDatabase ?= null
        fun getInstance(context:Context):LoginDatabase{
            return LoginInstance ?: synchronized(LoginDatabase::class.java){
                val instance = Room.databaseBuilder(context.applicationContext,LoginDatabase::class.java,"LoginDatabase").fallbackToDestructiveMigration().build()
                LoginInstance=instance
                instance
            }
        }
    }
}
