package com.crossasyst.manyTomany.mapper;

import com.crossasyst.manyTomany.entity.DepartmentEntity;
import com.crossasyst.manyTomany.model.DepartmentRequest;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    public DepartmentEntity DeptModelToEntity(DepartmentRequest departmentRequest);

    public DepartmentRequest entityToDeptModel(DepartmentEntity departmentEntity);

    public List<DepartmentRequest> entityToDeptModels(List<DepartmentEntity> departmentEntities);
}
