package com.crossasyst.manyTomany.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "department")
public class DepartmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long deptId;
    @Column(name = "dept_Name")
    private String deptName;

    @ManyToMany(mappedBy = "department")
    private List<EmployeeEntity> employee;

}
