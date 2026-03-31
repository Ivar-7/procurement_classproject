package com.example.procurement.features.departments.service;

import com.example.procurement.features.departments.dao.DepartmentDao;
import com.example.procurement.features.departments.model.Department;

import java.util.List;

public class DepartmentService {

    private final DepartmentDao departmentDao = new DepartmentDao();

    public List<Department> getAllDepartments() {
        return departmentDao.findAll();
    }

    public void createDepartment(Department department) {
        departmentDao.create(department);
    }
}
