package com.udacity.jdnd.course3.critter.service.impl;

import com.udacity.jdnd.course3.critter.entity.Employees;
import com.udacity.jdnd.course3.critter.repository.EmployeesRepository;
import com.udacity.jdnd.course3.critter.service.EmployeesService;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployeesServiceImpl implements EmployeesService {

    @Autowired
    EmployeesRepository employeesRepository;
    @Override
    public Employees saveEmployee(Employees employees) {
        return employeesRepository.save(employees);
    }

    @Override
    public Employees getEmployeeById(Long id) {
        return employeesRepository.findById(id).get();
    }

    @Override
    public void setAvailability(Set<DayOfWeek> daysAvailable, Long id) {
        Employees employees = employeesRepository.findById(id).get();
        employees.setDaysAvailable(daysAvailable);
        employeesRepository.save(employees);
    }

    @Override
    public List<Employees> findEmployeesForService(Set<EmployeeSkill> skills, LocalDate date) {
        List<Employees> employees = employeesRepository.findAll();
        List<Employees> employeesMatching = employees.stream()
                .filter(employee -> employee.getSkills().containsAll(skills))
                .filter(employee -> employee.getDaysAvailable().contains(date.getDayOfWeek()))
                .collect(Collectors.toList());
        return employeesMatching;
    }

    @Override
    public Set<Employees> findEmployeeByListIds(List<Long> ids) {
        return employeesRepository.findEmployeesByListIds(ids);
    }
}
