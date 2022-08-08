package com.example.example_kakaologinapi.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(@PrimaryKey val userId:String, val userName:String, val userPw:String)
