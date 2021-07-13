package com.example.hw_54.models

import androidx.room.Relation

data class EmployeeWithDepartment(
    val name: String? = null,
    val salary: Int? = null,
    val department_id: Long? = null,
    @Relation(parentColumn = "department_id", entityColumn = "id", entity = Department::class)
    val department_name: DepartmentName? = null
)
