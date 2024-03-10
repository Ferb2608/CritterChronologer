package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.entity.Customers;
import com.udacity.jdnd.course3.critter.entity.Employees;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.service.CustomersService;
import com.udacity.jdnd.course3.critter.service.EmployeesService;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    CustomersService customersService;
    @Autowired
    EmployeesService employeesService;

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        Customers customers = new Customers();
        BeanUtils.copyProperties(customerDTO, customers);
        customers = customersService.createCustomer(customers);
        customerDTO.setId(customers.getId());
        return customerDTO;
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        List<Customers> customers = customersService.getAllCustomers();
        for(Customers c: customers){
            CustomerDTO customerDTO = new CustomerDTO();
            BeanUtils.copyProperties(c, customerDTO);
            customerDTO.setPetIds(c.getPets().stream().map(Pet::getId).collect(Collectors.toList()));
            customerDTOS.add(customerDTO);
        }
        return customerDTOS;
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
        CustomerDTO customerDTO = new CustomerDTO();
        Customers customers = customersService.getOwnerByPet(petId);
        BeanUtils.copyProperties(customers, customerDTO);
        customerDTO.setPetIds(customers.getPets().stream().map(Pet::getId).collect(Collectors.toList()));
        return customerDTO;
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employees employees = new Employees();
        BeanUtils.copyProperties(employeeDTO, employees);
        employees = employeesService.saveEmployee(employees);
        employeeDTO.setId(employees.getId());
        return employeeDTO;
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        Employees employees = employeesService.getEmployeeById(employeeId);
        EmployeeDTO employeeDTO = new EmployeeDTO();
        BeanUtils.copyProperties(employees, employeeDTO);
        return employeeDTO;
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        employeesService.setAvailability(daysAvailable, employeeId);
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        List<Employees> employees = employeesService.findEmployeesForService(employeeDTO.getSkills(), employeeDTO.getDate());
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();
        for (Employees e: employees){
            EmployeeDTO employeeDTOTerm = new EmployeeDTO();
            BeanUtils.copyProperties(e, employeeDTOTerm);
            employeeDTOS.add(employeeDTOTerm);
        }
        return employeeDTOS;
    }

}
