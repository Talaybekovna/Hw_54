package com.example.hw_54

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.hw_54.databinding.ActivityMainBinding
import com.example.hw_54.models.Department
import com.example.hw_54.models.Employee
import com.example.hw_54.storage.EmployeeDepartmentDatabase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val db by lazy {
        EmployeeDepartmentDatabase.getInstance(applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getDepartmentWithEmployees()
    }

    private fun getEmployeeFromInput(): Employee {
        val employeeId = binding.etId.text?.toString().takeIf { !it.isNullOrEmpty() }?.toLong()
        val employeeName = binding.etName.text.toString()
        val salary = binding.etSalary.text?.toString().takeIf { !it.isNullOrEmpty() }?.toInt()
        val departmentId = binding.etDepartmentId.text?.toString().takeIf { !it.isNullOrEmpty() }?.toLong()

        return Employee(id = employeeId, name = employeeName, salary = salary, department_id = departmentId)
    }

    private fun insertEmployee() {
        binding.btnInsert.setOnClickListener {
            db.employeeDao()
                .insert(getEmployeeFromInput())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        }
    }

    @SuppressLint("CheckResult")
    private fun getEmployeeWithDepartment() {
        db.employeeDao()
            .getEmployeesWithDepartments()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.i(TAG_Employee,"Employees: $it")
            },{

            })
    }

    private fun getDepartmentFromInput(): Department {
        val departmentId = binding.etId.text?.toString().takeIf { !it.isNullOrEmpty() }?.toLong()
        val departmentName = binding.etName.text.toString()

        return Department(id = departmentId, name = departmentName)
    }



    private fun insertDepartment() {
        binding.btnInsert.setOnClickListener {
            db.departmentDao()
                .insert(getDepartmentFromInput())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        }
    }

    @SuppressLint("CheckResult")
    private fun getDepartmentWithEmployees() {
        db.departmentDao()
            .getDepartmentsWithEmployees()
            .subscribeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.i(TAG_Department,"Departments: $it")
            },{

            })
    }

    companion object {
        const val TAG_Employee = "Employee"
        const val TAG_Department = "Department"
    }

}