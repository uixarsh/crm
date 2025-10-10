package org.crm.service;
import java.util.List;

import org.crm.entity.Customer;
import org.crm.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {

  @Autowired
  private CustomerRepository repository;

  public List<Customer> getAllCustomers() { 
    return repository.findAll(); 
  }

  public Customer getCustomerById(Long id) { 
    return repository.findById(id).orElse(null); 
  }

  public Customer createCustomer(Customer c) { 
    return repository.save(c); 
  }

  public Customer updateCustomer (Long id, Customer c) {
    Customer existingCustomer = getCustomerById(id);
    existingCustomer.setFirstName(c.getFirstName());
    existingCustomer.setLastName(c.getLastName());
    existingCustomer.setEmail(c.getEmail());
    return repository.save(existingCustomer);
  }

  public void deleteCustomer(Long id) { 
    repository.deleteById(id); 
  }
}