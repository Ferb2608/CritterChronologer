package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.entity.Customers;
import com.udacity.jdnd.course3.critter.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Repository
@Transactional
public interface PetRepository extends JpaRepository<Pet, Long> {
    @Query("SELECT p FROM Pet p JOIN p.owner WHERE p.owner.id = :id")
    List<Pet> findByOwnerId(@Param("id") Long id);
    @Query("SELECT p FROM Pet p WHERE p.id IN :ids")
    Set<Pet> findPetByListIds(@Param("ids") List<Long> ids);
}
