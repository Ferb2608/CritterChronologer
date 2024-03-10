package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Customers;

import java.util.List;

public interface CustomersService {
    Customers findById(Long id);
    Customers createCustomer(Customers customers);
    List<Customers> getAllCustomers();
    Customers getOwnerByPet(Long petId);
}
