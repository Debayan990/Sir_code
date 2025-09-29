package org.reni.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DepartmentDto {
    private int departmentId;
    private String departmentName;
    private int locationId;
}
