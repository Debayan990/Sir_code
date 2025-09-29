package org.reni.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.lang.reflect.DeclareParents;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmployeeDetailsDto {
    EmployeeDto employee;
    private DepartmentDto department;
    private LocationDto location;

}
