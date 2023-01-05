package com.obeme.customer;

import com.obeme.clients.fraud.FraudCheckResponse;
import com.obeme.clients.fraud.FraudClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public record CustomerService(CustomerRepository customerRepository, RestTemplate restTemplate, FraudClient fraudClient) {
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
        //todo: check if fraudster using restTemplate to communicate
//        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject("http://FRAUD/api/v1/fraud-check/{customerId}",
//                FraudCheckResponse.class,
//                customer.getId());

        //todo: check if fraudster using openfeign to communicate
        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());

        if (fraudCheckResponse.isFraudster()) {
            throw new RuntimeException("Man's a fraudster!!");
        }
        //todo:  send notification
    }
}
