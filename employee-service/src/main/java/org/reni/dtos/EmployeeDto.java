package org.reni.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class EmployeeDto {
    private int id;
    private String name;
    private String gender;
    private int age;
    private double salary;
    private int departmentId;
}
