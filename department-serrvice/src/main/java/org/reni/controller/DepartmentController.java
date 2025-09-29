package org.reni.controller;

import lombok.RequiredArgsConstructor;

import org.reni.dtos.DepartmentDto;
import org.reni.service.DepartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;
    @GetMapping
    public ResponseEntity<?> getAllDepartments() {
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    //postmapping
    @PostMapping
    public ResponseEntity<?> addDepartment(@RequestBody DepartmentDto departmentDto) {
        return ResponseEntity.status(201).body(departmentService.addDepartment(departmentDto));
    }
    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable int id,@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(departmentService.getDepartmentById(id));
    }

}
