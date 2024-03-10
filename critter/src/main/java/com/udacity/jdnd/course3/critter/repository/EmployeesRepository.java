package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.entity.Employees;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Repository
@Transactional
public interface EmployeesRepository extends JpaRepository<Employees, Long> {
    @Query("SELECT e FROM Employees e WHERE e.id IN :ids")
    Set<Employees> findEmployeesByListIds(@Param("ids") List<Long> ids);
}
