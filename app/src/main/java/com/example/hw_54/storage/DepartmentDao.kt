package com.example.hw_54.storage

import androidx.room.Dao
import androidx.room.Insert
import com.example.hw_54.models.Department
import io.reactivex.Completable

@Dao
interface DepartmentDao {

    @Insert
    fun insert(department: Department): Completable
}