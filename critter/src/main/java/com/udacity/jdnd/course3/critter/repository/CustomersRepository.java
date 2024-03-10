package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.entity.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CustomersRepository extends JpaRepository<Customers, Long> {
    @Query("SELECT c FROM Customers c JOIN c.pets p WHERE p.id = :id")
    Customers findOwnerByPetId(@Param("id") Long petId);
}
