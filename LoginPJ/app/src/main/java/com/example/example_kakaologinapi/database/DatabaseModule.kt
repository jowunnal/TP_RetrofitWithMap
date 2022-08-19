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
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule{

    @Singleton
    @Provides
    fun provideDatabaseDao(loginDatabase: LoginDatabase):LoginDao{
        return loginDatabase.loginDao()
    }

    @Singleton
    @Provides
    fun provideDatabaseInstance(@ApplicationContext context:Context):LoginDatabase{
        return Room.databaseBuilder(context,LoginDatabase::class.java,"LoginDatabase").build()
    }

    /*
    companion object{
        private var LoginInstance : LoginDatabase ?= null
        fun getInstance(context:Context):LoginDatabase{
            return LoginInstance ?: synchronized(LoginDatabase::class.java){
                val instance = Room.databaseBuilder(context.applicationContext,LoginDatabase::class.java,"LoginDatabase").build()
                LoginInstance=instance
                instance
            }
        }
    }*/
}
