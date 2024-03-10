package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Customers;
import com.udacity.jdnd.course3.critter.entity.Employees;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface EmployeesService {
    Employees saveEmployee(Employees employees);
    Employees getEmployeeById(Long id);
    void setAvailability(Set<DayOfWeek> daysAvailable, Long id);
    List<Employees> findEmployeesForService(Set<EmployeeSkill> skills, LocalDate date);
    Set<Employees> findEmployeeByListIds(List<Long> ids);
}
