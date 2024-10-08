package com.example.learnSpringBoot.springTutorial.controllers;

import com.example.learnSpringBoot.springTutorial.dto.EmployeeDTO;
import com.example.learnSpringBoot.springTutorial.services.EmployeeService;
import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

    final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/{id}")
    public EmployeeDTO getEmployeeById(@PathVariable("id") Long employeeID){
        return employeeService.getEmployeeById(employeeID);
    }

    @PostMapping
    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO employeeDTO){
        return employeeService.addNewEmployee(employeeDTO);
    }

}
