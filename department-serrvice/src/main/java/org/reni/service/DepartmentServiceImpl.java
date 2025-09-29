package org.reni.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import org.reni.dtos.DepartmentDto;
import org.reni.entities.Department;
import org.reni.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;
    @Override
    public List<DepartmentDto> getAllDepartments() {
        return departmentRepository.findAll()
                .stream().map(dept->modelMapper.map(dept,DepartmentDto.class))
                .toList();
    }

    @Override
    public DepartmentDto addDepartment(DepartmentDto departmentDto) {

      var department=
              modelMapper.map(departmentDto, Department.class);
        System.out.println(department);
        var savedDepartment=
                departmentRepository.save(department);
        return modelMapper.map(savedDepartment, DepartmentDto.class);
    }

    @Override
    public DepartmentDto getDepartmentById(int departmentId) {
        return departmentRepository.findById(departmentId)
                .map(dept->modelMapper.map(dept,DepartmentDto.class))
                .orElseThrow(()->new RuntimeException("Department not found"));
    }
}
