package org.reni.service;



import org.reni.dtos.DepartmentDto;

import java.util.List;

public interface DepartmentService {

    List<DepartmentDto> getAllDepartments();
    DepartmentDto addDepartment(DepartmentDto departmentDto);
    DepartmentDto getDepartmentById(int departmentId);
}
