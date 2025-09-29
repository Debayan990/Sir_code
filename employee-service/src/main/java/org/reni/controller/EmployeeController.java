package org.reni.controller;


import lombok.RequiredArgsConstructor;
import org.reni.dtos.EmployeeDto;
import org.reni.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor

public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<?> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<?> getEmployeeDetailsById(@PathVariable int id,@RequestHeader("Authorization") String token) {
        
        return ResponseEntity.ok(employeeService.getEmployeeDetailsById(id,token));
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> addEmployee(@RequestBody EmployeeDto employeeDto) {
        return ResponseEntity.status(201).body(employeeService.addEmployee(employeeDto));
    }
}
