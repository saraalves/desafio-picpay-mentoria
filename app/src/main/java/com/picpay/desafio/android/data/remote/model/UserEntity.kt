package com.picpay.desafio.android.data.remote.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UserData")
data class UserEntity (
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo
    var name: String? = "",
    @ColumnInfo
    var username: String? = "",
    @ColumnInfo
    var img: String? = ""
)