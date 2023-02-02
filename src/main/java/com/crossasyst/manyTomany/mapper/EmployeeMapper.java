package com.crossasyst.manyTomany.mapper;

import com.crossasyst.manyTomany.entity.DepartmentEntity;
import com.crossasyst.manyTomany.entity.EmployeeEntity;
import com.crossasyst.manyTomany.model.DepartmentRequest;
import com.crossasyst.manyTomany.model.EmployeeRequest;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    public EmployeeEntity modelToEntity(EmployeeRequest employeeRequest);

    public EmployeeRequest entityToModel(EmployeeEntity employeeEntity);

    public List<EmployeeRequest> entityToModels(List<EmployeeEntity> employeeEntity);

}
