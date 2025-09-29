package org.reni.service;

import org.reni.dtos.EmployeeDetailsDto;
import org.reni.dtos.EmployeeDto;

import java.util.List;

public interface EmployeeService {

        List<EmployeeDto> getAllEmployees();
        EmployeeDto addEmployee(EmployeeDto employeeDto);

    EmployeeDetailsDto getEmployeeDetailsById(int employeeId,String token);
}
