package com.godfreykivaru.ems;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.godfreykivaru.ems.entity.Employee;
import com.godfreykivaru.ems.repository.EmployeeRepository;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "admin", password = "password")
public class EmployeeControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Employee employee;

    @BeforeEach
    void setUp(){
        employeeRepository.deleteAll();
        employee = new Employee(null, "Godfrey", "Kivaru", "kivarugodfrey@gmail.com", "Engineering", 2500000.00);
        employeeRepository.save(employee);
    }

    @Test
    void shouldCreateEmployee() throws Exception {
        mockMvc.perform(post("/api/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value("Godfrey"));
    }

    @Test
    void shouldReturnAllEmployees() throws Exception {
        mockMvc.perform(get("/api/employees") 
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].firstName").value("Godfrey"));
    }

    @Test
    void shouldReturnEmployeeById() throws Exception {
        Employee savedEmployee = employeeRepository.save(employee);

        mockMvc.perform(get("/api/employees/{id}", savedEmployee.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("kivarugodfrey@gmail.com"));
    }

    @Test
    void shouldUpdateEmployee() throws Exception {
        Employee savedEmployee = employeeRepository.save(employee);
        savedEmployee.setFirstName("Hellen");

        mockMvc.perform(put("/api/employees/{id}", savedEmployee.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(savedEmployee)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Hellen"));
    }

    @Test
    void shouldDeleteEmployee() throws Exception {
        Employee savedEmployee = employeeRepository.save(employee);

        mockMvc.perform(delete("/api/employees/{id}", savedEmployee.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

}
