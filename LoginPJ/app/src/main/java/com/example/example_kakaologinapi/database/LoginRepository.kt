package com.example.example_kakaologinapi.database

class LoginRepository(private val loginDao:LoginDao) {
    fun getUser() = loginDao.getUser()

    suspend fun addUser(user: User)=loginDao.addUser(user)
    suspend fun deleteUser(user: User)=loginDao.deleteUser(user)
}