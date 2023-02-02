package com.crossasyst.manyTomany.service;

import com.crossasyst.manyTomany.entity.DepartmentEntity;
import com.crossasyst.manyTomany.entity.EmployeeEntity;
import com.crossasyst.manyTomany.mapper.EmployeeMapper;
import com.crossasyst.manyTomany.model.EmployeeRequest;
import com.crossasyst.manyTomany.model.EmployeeResponse;
import com.crossasyst.manyTomany.repository.DepartmentRepository;
import com.crossasyst.manyTomany.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final DepartmentService departmentService;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper, DepartmentService departmentService, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
        this.departmentService = departmentService;
        this.departmentRepository = departmentRepository;
    }

    public List<EmployeeRequest> getAllEmployee() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        List<EmployeeRequest> employeeRequest = employeeMapper.entityToModels(employeeEntities);
        return employeeRequest;
    }

    //@Cacheable(cacheNames = "employee", key = "empId")
    public EmployeeRequest getById(Long empId) {
        EmployeeEntity employeeEntity = employeeRepository.findById(empId).get();
        EmployeeRequest employeeRequest = employeeMapper.entityToModel(employeeEntity);
        return employeeRequest;

    }

    public EmployeeResponse createEmployee(EmployeeRequest employeeRequest) {
        EmployeeEntity employeeEntity = employeeRepository.save(employeeMapper.modelToEntity(employeeRequest));
        EmployeeResponse employeeResponse = new EmployeeResponse();
        employeeResponse.setEmpId(employeeEntity.getEmpId());
        return employeeResponse;

    }

   // @CacheEvict(cacheNames = "employee", key = "#empId")
    public void deleteEmployee(Long empId) {
        employeeRepository.deleteById(empId);
    }

 //   @CachePut(cacheNames = "employee", key = "#empId")
    public EmployeeRequest updateEmployee(Long empId, EmployeeRequest employeeRequest) {
        EmployeeEntity employeeEntity1 = employeeMapper.modelToEntity(employeeRequest);
        employeeEntity1.setEmpId(empId);
        employeeEntity1.getDepartment();
        EmployeeEntity employeeEntity = employeeRepository.save(employeeEntity1);

        return employeeRequest;
    }

    public EmployeeRequest addDepartmentToEmployee(Long empId, Long deptId)
    {
        EmployeeEntity employeeEntity = employeeRepository.findById(empId).get();
        DepartmentEntity departmentEntity = departmentRepository.findById(deptId).get();


        List<DepartmentEntity> departments = employeeEntity.getDepartment();
        departments.add(departmentEntity);
        employeeRepository.save(employeeEntity);
        return employeeMapper.entityToModel(employeeEntity);

    }

    public EmployeeRequest removeDepartmentFromEmployee(Long empId, Long deptId)
    {
        EmployeeEntity employeeEntity = employeeRepository.findById(empId).get();
        DepartmentEntity departmentEntity = departmentRepository.findById(deptId).get();


        List<DepartmentEntity> departments = employeeEntity.getDepartment();
        departments.remove(departmentEntity);
        employeeRepository.save(employeeEntity);
        return employeeMapper.entityToModel(employeeEntity);

    }
}
