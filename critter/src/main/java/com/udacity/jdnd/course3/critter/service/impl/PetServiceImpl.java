package com.udacity.jdnd.course3.critter.service.impl;

import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class PetServiceImpl implements PetService {

    @Autowired
    PetRepository petRepository;
    @Override
    public Pet savePet(Pet pet) {
        return petRepository.save(pet);
    }

    @Override
    public Pet findById(Long id) {
        return petRepository.findById(id).get();
    }

    @Override
    public List<Pet> findAllPets() {
        return petRepository.findAll();
    }

    @Override
    public List<Pet> getPetByOwner(Long id) {
        return petRepository.findByOwnerId(id);
    }

    @Override
    public Set<Pet> findPetsByListIds(List<Long> ids) {
        return petRepository.findPetByListIds(ids);
    }
}
