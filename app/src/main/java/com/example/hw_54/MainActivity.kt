package com.example.hw_54

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hw_54.databinding.ActivityMainBinding
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

        insertEmployee()
//        deleteEmployee()
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

    private fun deleteEmployee() {
        binding.btnDelete.setOnClickListener {
            db.employeeDao()
                .delete(getEmployeeFromInput())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        }
    }
}