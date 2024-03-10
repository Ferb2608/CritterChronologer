package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.entity.Customers;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.service.CustomersService;
import com.udacity.jdnd.course3.critter.service.PetService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    PetService petService;
    @Autowired
    CustomersService customersService;
    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        PetDTO result = new PetDTO();
        Pet pet = new Pet();
        BeanUtils.copyProperties(petDTO, pet);
        Customers customers = customersService.findById(petDTO.getOwnerId());
        pet.setOwner(customers);
        pet = petService.savePet(pet);
        Set<Pet> pets = customers.getPets();
        pets.add(pet);
        customers.setPets(pets);
        BeanUtils.copyProperties(pet, result);
        result.setOwnerId(petDTO.getOwnerId());
        return result;
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        PetDTO petDTO = new PetDTO();
        Pet pet = petService.findById(petId);
        BeanUtils.copyProperties(pet, petDTO);
        petDTO.setOwnerId(pet.getOwner().getId());
        return petDTO;
    }

    @GetMapping
    public List<PetDTO> getPets(){
        List<PetDTO> petDTOS = new ArrayList<>();
        List<Pet> pets = petService.findAllPets();
        for(Pet p : pets){
            PetDTO petDTO = new PetDTO();
            BeanUtils.copyProperties(p, petDTO);
            petDTO.setOwnerId(p.getOwner().getId());
            petDTOS.add(petDTO);
        }
        return petDTOS;
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        List<PetDTO> petDTOS = new ArrayList<>();
        List<Pet> pets = petService.getPetByOwner(ownerId);
        for(Pet p : pets){
            PetDTO petDTO = new PetDTO();
            BeanUtils.copyProperties(p, petDTO);
            petDTO.setOwnerId(p.getOwner().getId());
            petDTOS.add(petDTO);
        }
        return petDTOS;
    }
}
