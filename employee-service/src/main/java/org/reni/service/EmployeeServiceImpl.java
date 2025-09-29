package org.reni.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.reni.clients.DepartmentClient;
import org.reni.clients.LocationClient;
import org.reni.dtos.DepartmentDto;
import org.reni.dtos.EmployeeDetailsDto;
import org.reni.dtos.EmployeeDto;
import org.reni.dtos.LocationDto;
import org.reni.entity.Employee;
import org.reni.repository.EmployeeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    private final DepartmentClient departmentClient;
    private final LocationClient locationClient;

    @Override
    public List<EmployeeDto> getAllEmployees() {
        return employeeRepository
                .findAll()
                .stream()
                .map(emp->
                        modelMapper.map(emp, EmployeeDto.class))
                .toList();
    }

    @Override
    public EmployeeDto addEmployee(EmployeeDto employeeDto) {
        return modelMapper.map(
                employeeRepository.save(
                        modelMapper.map(employeeDto, Employee.class)
                ), EmployeeDto.class
        );
    }

    @Override
    public EmployeeDetailsDto getEmployeeDetailsById(int employeeId,String token) {
       EmployeeDto employeeDto=
               modelMapper.map(
                       employeeRepository.findById(employeeId).orElseThrow(
                               ()->new RuntimeException("Employee not found")
                       ), EmployeeDto.class
               );
        ResponseEntity<DepartmentDto> responseEntity=departmentClient.
                getDepartmentById(employeeDto.getDepartmentId(),token);
        var dept=responseEntity.getBody();
        ResponseEntity<LocationDto> locationResponse=
                locationClient.getLocationById(dept.getLocationId(),token);

        EmployeeDetailsDto employeeDetailsDto=EmployeeDetailsDto
                .builder().employee(employeeDto)
                .department(dept)
                .location(locationResponse.getBody())
                .build();
        return employeeDetailsDto;






    }
}
