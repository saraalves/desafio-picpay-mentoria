package com.picpay.desafio.android.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.picpay.desafio.android.data.remote.model.UserEntity

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUsers(userEntity: List<UserEntity>)

    @Query("SELECT * FROM UserData")
    fun getUsers(): List<UserEntity>

    @Query("DELETE FROM UserData WHERE id = :id")
    fun deleteUser(id: String)

    @Query("DELETE FROM UserData")
    fun deleteUsers()
}