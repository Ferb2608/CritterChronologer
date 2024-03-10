package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    @Query("SELECT s FROM Schedule s JOIN s.employees e WHERE e.id = :id")
    List<Schedule> findScheduleByEmployeeId(@Param("id") Long employeeId);
    @Query("SELECT s FROM Schedule s JOIN s.pets p WHERE p.id = :id")
    List<Schedule> findScheduleByPetId(@Param("id") Long petId);

    @Query("SELECT s FROM Schedule s JOIN s.pets p WHERE p.owner.id = :id")
    List<Schedule> findScheduleByCustomerId(@Param("id") Long customerId);
}
