package com.example.hw_54.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Employee(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val name: String? = null,
    val salary: Int? = null,
    val department_id: Long? = null
)
