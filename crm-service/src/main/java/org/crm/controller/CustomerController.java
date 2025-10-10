package org.crm.controller;

import java.util.List;

import org.crm.entity.Customer;
import org.crm.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

  @Autowired
  private CustomerService service;

  @GetMapping
  public List<Customer> getCustomers() { 
    return service.getAllCustomers(); 
  }

  @GetMapping("/{id}")
  public Customer getById(@PathVariable Long id) { 
    return service.getCustomerById(id); 
  }

  @PostMapping
  public Customer create(@RequestBody Customer c) { 
    return service.createCustomer(c); 
  }

  @PutMapping("/{id}")
  public ResponseEntity<Customer> update(@PathVariable Long id, @RequestBody Customer customer) {
    Customer updated = service.updateCustomer(id, customer);
    return ResponseEntity.ok(updated);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) { 
    service.deleteCustomer(id); 
  }
}