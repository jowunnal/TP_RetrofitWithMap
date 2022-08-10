package com.example.example_kakaologinapi.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(@PrimaryKey var userId:String, var userName:String, var userPw:String)
