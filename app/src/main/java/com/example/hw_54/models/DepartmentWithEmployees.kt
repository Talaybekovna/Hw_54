package com.example.hw_54.models

import androidx.room.Relation

data class DepartmentWithEmployees(
    val id: Long? = null,
    val name: String? = null,
    @Relation(parentColumn = "id", entityColumn = "department_id", entity = Employee::class)
    val employees: List<EmployeeNameAndSalary>? = null
)
