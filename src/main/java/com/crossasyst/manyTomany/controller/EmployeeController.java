package com.crossasyst.manyTomany.controller;


import com.crossasyst.manyTomany.model.EmployeeRequest;
import com.crossasyst.manyTomany.model.EmployeeResponse;
import com.crossasyst.manyTomany.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(value = "/employee")
    List<EmployeeRequest> getAllEmployee() {
        return employeeService.getAllEmployee();
    }

    @GetMapping(value = "/employee/{empId}")
    public ResponseEntity<EmployeeRequest> getById(@PathVariable Long empId) {
        EmployeeRequest employeeRequest = employeeService.getById(empId);
        return new ResponseEntity<>(employeeRequest, HttpStatus.OK);
    }

    @PostMapping(value = "/employee")
    public ResponseEntity<EmployeeResponse> createEmployee(@RequestBody EmployeeRequest employeeRequest) {
        EmployeeResponse employeeResponse = employeeService.createEmployee(employeeRequest);

        return new ResponseEntity<>(employeeResponse, HttpStatus.OK);
    }

    @PutMapping(value = "/employee/{empId}")
    public ResponseEntity<EmployeeRequest> updateEmployee(@PathVariable Long empId, @RequestBody EmployeeRequest employeeRequest) {

        EmployeeRequest employeeRequest1 = employeeService.updateEmployee(empId, employeeRequest);
        return new ResponseEntity<>(employeeRequest1, HttpStatus.OK);
    }

    @DeleteMapping(value = "/employee/{empId}")
    public void deleteEmployee(@PathVariable Long empId) {
        employeeService.deleteEmployee(empId);
    }

    @PutMapping(value = "/employees/{empId}/departments/{deptId}")
    public ResponseEntity<EmployeeRequest> addDeptToEmp(@PathVariable Long empId, @PathVariable Long deptId)
    {
        return new ResponseEntity<>(employeeService.addDepartmentToEmployee(empId,deptId),HttpStatus.OK);
    }

}