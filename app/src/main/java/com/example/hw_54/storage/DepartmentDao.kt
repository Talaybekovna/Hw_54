package com.example.hw_54.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.hw_54.models.Department
import com.example.hw_54.models.DepartmentWithEmployees
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface DepartmentDao {

    @Insert
    fun insert(department: Department): Completable

    @Transaction
    @Query("SELECT id, name from Department")
    fun getDepartmentsWithEmployees(): Single<List<DepartmentWithEmployees>>
}