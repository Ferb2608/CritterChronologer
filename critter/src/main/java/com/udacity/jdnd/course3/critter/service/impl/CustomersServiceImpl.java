package com.udacity.jdnd.course3.critter.service.impl;

import com.udacity.jdnd.course3.critter.entity.Customers;
import com.udacity.jdnd.course3.critter.repository.CustomersRepository;
import com.udacity.jdnd.course3.critter.service.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomersServiceImpl implements CustomersService {

    @Autowired
    CustomersRepository customersRepository;

    @Override
    public Customers findById(Long id) {
        return customersRepository.findById(id).get();
    }

    @Override
    public Customers createCustomer(Customers customers) {
        return customersRepository.save(customers);
    }

    @Override
    public List<Customers> getAllCustomers() {
        return customersRepository.findAll();
    }

    @Override
    public Customers getOwnerByPet(Long petId) {
        return customersRepository.findOwnerByPetId(petId);
    }
}
