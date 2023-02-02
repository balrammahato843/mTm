package com.crossasyst.manyTomany.controller;


import com.crossasyst.manyTomany.model.DepartmentRequest;
import com.crossasyst.manyTomany.model.DepartmentResponse;
import com.crossasyst.manyTomany.model.EmployeeRequest;
import com.crossasyst.manyTomany.service.DepartmentService;
import com.crossasyst.manyTomany.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentController {
    private final DepartmentService departmentService;

    private final EmployeeService employeeService;

    @Autowired
    public DepartmentController(DepartmentService departmentService, EmployeeService employeeService) {
        this.departmentService = departmentService;
        this.employeeService = employeeService;
    }

    @GetMapping(value = "/departments")
    List<DepartmentRequest> getAllDepartment() {
        return departmentService.getAllDepartments();
    }

    @GetMapping(value = "/departments/{deptId}")
    public ResponseEntity<DepartmentRequest> getByDeptId(@PathVariable Long deptId) {
        DepartmentRequest departmentRequest = departmentService.getByDeptId(deptId);
        return new ResponseEntity<>(departmentRequest, HttpStatus.OK);
    }

    @PostMapping(value = "/departments")
    public ResponseEntity<DepartmentResponse> createDepartment(@RequestBody DepartmentRequest departmentRequest) {
        DepartmentResponse departmentResponse = departmentService.createDepartment(departmentRequest);
        return new ResponseEntity<>(departmentResponse, HttpStatus.OK);
    }

    @DeleteMapping(value = "/departments/{deptId}")
    public void deleteDepartment(@PathVariable Long deptId) {
        departmentService.deleteDepartment(deptId);
    }


    @DeleteMapping(value = "/employees/{empId}/departments/{deptId}")
    public ResponseEntity<EmployeeRequest> removeDeptFromEmp(@PathVariable Long empId,@PathVariable Long deptId)
    {
        return new ResponseEntity<>(employeeService.removeDepartmentFromEmployee(empId, deptId),HttpStatus.OK);
    }
}
