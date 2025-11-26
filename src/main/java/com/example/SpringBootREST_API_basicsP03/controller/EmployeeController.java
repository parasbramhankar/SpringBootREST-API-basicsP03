package com.example.SpringBootREST_API_basicsP03.controller;


import com.example.SpringBootREST_API_basicsP03.entity.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    Map<Integer, Employee>employeeMap=new HashMap<>();
    @GetMapping
    public ResponseEntity<List<Employee>>getAllEmployee(){
        return ResponseEntity.ok(new ArrayList<>(employeeMap.values()));
    }

    @PostMapping
    public ResponseEntity<Employee>createEmployee(@RequestBody Employee employee){
        employeeMap.put(employee.getId(), employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(employee);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee>getEmployeeById(@PathVariable int id){
        Employee employee= employeeMap.get(id);

        if(employee==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void>updateEmployee(@PathVariable int id,@RequestBody Employee employee){
        Employee existing=employeeMap.get(id);

        if(existing==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        employeeMap.put(employee.getId(),employee);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/{id}/salary")
    public ResponseEntity<Void>updateSalary(@PathVariable int id,@RequestBody double newSalary){
        Employee employee= employeeMap.get(id);

        if(employee==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        employee.setSalary(newSalary);

        employeeMap.put(id,employee);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployeeById(@PathVariable int id){
        Employee employee=employeeMap.remove(id);

        if(employee==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}