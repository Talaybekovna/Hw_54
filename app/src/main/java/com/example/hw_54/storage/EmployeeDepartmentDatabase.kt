package com.example.hw_54.storage

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.hw_54.models.Employee
import com.example.hw_54.models.Department

@Database(
    entities = [Employee::class, Department::class],
    version = 1,
    exportSchema = false
)
abstract class EmployeeDepartmentDatabase: RoomDatabase() {

    abstract fun employeeDao(): EmployeeDao

    companion object {
        private const val DB_NAME = "employee_department_db"

        private var DB: EmployeeDepartmentDatabase? = null

        fun getInstance(context: Context): EmployeeDepartmentDatabase {
            if (DB == null) {
                DB = Room.databaseBuilder(
                    context,
                    EmployeeDepartmentDatabase::class.java,
                    DB_NAME
                )
                    .build()
            }
            return DB as EmployeeDepartmentDatabase
        }
    }
}