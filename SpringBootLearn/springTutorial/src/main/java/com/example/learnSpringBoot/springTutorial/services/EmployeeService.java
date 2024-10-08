package com.example.learnSpringBoot.springTutorial.services;

import com.example.learnSpringBoot.springTutorial.dto.EmployeeDTO;
import com.example.learnSpringBoot.springTutorial.entities.EmployeeEntity;
import com.example.learnSpringBoot.springTutorial.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    final EmployeeRepository employeeRepository;
    final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public EmployeeDTO getEmployeeById(Long id){
        EmployeeEntity employeeEntity = employeeRepository.getById(id);
        return modelMapper.map(employeeEntity,EmployeeDTO.class);
    }

    public EmployeeDTO addNewEmployee(EmployeeDTO employeeDTO){
        EmployeeEntity employeeEntity = modelMapper.map(employeeDTO, EmployeeEntity.class);
        EmployeeEntity result = employeeRepository.save(employeeEntity);
        return modelMapper.map(result,EmployeeDTO.class);
    }
}
