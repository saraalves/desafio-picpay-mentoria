package com.picpay.desafio.android.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.picpay.desafio.android.data.remote.model.UserEntity

@Dao
interface UserDao {
    @Insert
    suspend fun saveUsers(userEntity: List<UserEntity>)

    @Query("SELECT * FROM UserData")
    suspend fun getUsers(): List<UserEntity>

    @Query("DELETE FROM UserData WHERE id = :id")
    suspend fun deleteUser(id: Int)
}