package com.example.hw_54.storage

import androidx.room.*
import com.example.hw_54.models.Employee
import com.example.hw_54.models.EmployeeWithDepartment
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface EmployeeDao {

    @Insert
    fun insert(employee: Employee): Completable

    @Transaction
    @Query("SELECT name, salary, department_id from Employee")
    fun getEmployeesWithDepartments(): Single<List<EmployeeWithDepartment>>

    @Delete
    fun delete(employee: Employee): Completable
}