package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.entity.Employees;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.service.EmployeesService;
import com.udacity.jdnd.course3.critter.service.PetService;
import com.udacity.jdnd.course3.critter.service.ScheduleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;
    @Autowired
    PetService petService;
    @Autowired
    EmployeesService employeesService;
    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        ScheduleDTO result = new ScheduleDTO();
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleDTO, schedule);
        Set<Pet> pets = petService.findPetsByListIds(scheduleDTO.getPetIds());
        Set<Employees> employees = employeesService.findEmployeeByListIds(scheduleDTO.getEmployeeIds());
        schedule.setEmployees(employees);
        schedule.setPets(pets);
        schedule = scheduleService.createSchedule(schedule);
        BeanUtils.copyProperties(schedule, result);
        result.setPetIds(schedule.getPets().stream().map(Pet::getId).collect(Collectors.toList()));
        result.setEmployeeIds(schedule.getEmployees().stream().map(Employees::getId).collect(Collectors.toList()));
        return result;
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        List<Schedule> schedules = scheduleService.getAllSchedules();
        return transferDTOList(schedules);
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        List<Schedule> schedules = scheduleService.getScheduleForPet(petId);
        return transferDTOList(schedules);
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        List<Schedule> schedules = scheduleService.getScheduleForEmployee(employeeId);
        return transferDTOList(schedules);
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        List<Schedule> schedules = scheduleService.getScheduleForCustomer(customerId);
        return transferDTOList(schedules);
    }
    private List<ScheduleDTO> transferDTOList(List<Schedule> schedules){
        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();
        for(Schedule s: schedules){
            ScheduleDTO scheduleDTO = new ScheduleDTO();
            BeanUtils.copyProperties(s, scheduleDTO);
            scheduleDTO.setPetIds(s.getPets().stream().map(Pet::getId).collect(Collectors.toList()));
            scheduleDTO.setEmployeeIds(s.getEmployees().stream().map(Employees::getId).collect(Collectors.toList()));
            scheduleDTOS.add(scheduleDTO);
        }
        return scheduleDTOS;
    }
}
