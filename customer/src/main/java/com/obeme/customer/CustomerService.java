package com.obeme.customer;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public record CustomerService(CustomerRepository customerRepository, RestTemplate restTemplate) {
    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer
                .builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();

        //todo: check if email is valid
        //todo: check if email is taken
        List<Customer> allCustomers = customerRepository.findAll();
        for (Customer findCustomer : allCustomers) {
            if(findCustomer.getEmail().equals(customer.getEmail())) {
                throw new RuntimeException("Email already exists!!");
            }
        }
        customerRepository.saveAndFlush(customer); 
        //todo: check if fraudster
        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject("http://localhost:8081/api/v1/fraud-check/{customerId}",
                FraudCheckResponse.class,
                customer.getId());

        if (fraudCheckResponse.isFraudster()) {
            throw new RuntimeException("Man's a fraudster!!");
        }
        //todo:  send notification
    }
}
