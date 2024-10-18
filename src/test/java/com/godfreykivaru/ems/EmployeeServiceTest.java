package com.godfreykivaru.ems;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import static org.assertj.core.api.Assertions.assertThat;

import com.godfreykivaru.ems.entity.Employee;
import com.godfreykivaru.ems.repository.EmployeeRepository;
import com.godfreykivaru.ems.service.EmployeeService;

public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    private Employee employee;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
            employee = new Employee(1L, "Godfrey", "Kivaru", "kivarugodfrey@gmail.com", "Engineering", 2500000.0);
    }

    @Test
    void shouldReturnAllEmployees(){
        PageRequest pageable = PageRequest.of(0,10);
        Page<Employee> employeesPage = new PageImpl<>(List.of(employee));

        when(employeeRepository.findAll(pageable)).thenReturn(employeesPage);

        Page<Employee> employees = employeeService.getAllEmployees(pageable);

        assertThat(employees).hasSize(1);
        assertThat(employees.getContent().get(0).getFirstName()).isEqualTo("Godfrey");

        verify(employeeRepository, times(1)).findAll(pageable);
    }

    @Test
    void shouldReturnEmployeeById(){
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        Employee employee = employeeService.getEmployeeById(1L);

        assertThat(employee).isNotNull();
        assertThat(employee.getEmail()).isEqualTo("kivarugodfrey@gmail.com");

        verify(employeeRepository, times(1)).findById(1L);
    }

    @Test
    void shouldCreateEmployee(){
        when(employeeRepository.save(employee)).thenReturn(employee);

        Employee savedEmployee = employeeService.createEmployee(employee);

        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getLastName()).isEqualTo("Kivaru");

        verify(employeeRepository, times(1)).save(employee);
    }

    @Test
    void shouldDeleteEmployee(){
        doNothing().when(employeeRepository).deleteById(1L);

        employeeService.deleteEmployee(1L);

        verify(employeeRepository, times(1)).deleteById(1L);
    }

}
