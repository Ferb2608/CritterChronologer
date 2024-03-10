package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Pet;

import java.util.List;
import java.util.Set;

public interface PetService {
    Pet savePet(Pet pet);
    Pet findById(Long id);
    List<Pet> findAllPets();
    List<Pet> getPetByOwner(Long id);
    Set<Pet> findPetsByListIds(List<Long> ids);
}
