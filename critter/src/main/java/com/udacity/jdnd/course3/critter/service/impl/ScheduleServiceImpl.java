package com.udacity.jdnd.course3.critter.service.impl;

import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import com.udacity.jdnd.course3.critter.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    ScheduleRepository scheduleRepository;
    @Override
    public Schedule createSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    @Override
    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    @Override
    public List<Schedule> getScheduleForPet(Long petId) {
        return scheduleRepository.findScheduleByPetId(petId);
    }

    @Override
    public List<Schedule> getScheduleForEmployee(Long employeeId) {
        return scheduleRepository.findScheduleByEmployeeId(employeeId);
    }

    @Override
    public List<Schedule> getScheduleForCustomer(Long customerId) {
        return scheduleRepository.findScheduleByCustomerId(customerId);
    }
}
