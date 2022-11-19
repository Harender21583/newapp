package com.example.myapplication.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userInfo")
data class UserInfo(
    @PrimaryKey(autoGenerate = true)
    val id:Long,
    val name: String?,
    val mobile_no: String?,
    val password: String?,
    val gender: String?,

    )
