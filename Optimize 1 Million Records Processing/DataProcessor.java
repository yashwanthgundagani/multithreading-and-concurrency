package com.example.processor;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataProcessor {
    public void process(List<Customer> customers){
        long start = System.currentTimeMillis();

        for(Customer customer: customers){
            transform(customer); //sequential procesing
        }

        long end=System.currentTimeMillis();
        System.out.println("Time Taken: "+(end-start)+" ms");
    }

    private void transform(Customer customer){
        //mask email and uppercase name
        String maskedEmail=customer.getEmail().replaceAll("(?<=.).(?=.*@)","*");
        customer.setEmail(maskedEmail);
        customer.setName(customer.getName().toUpperCase());
    }
}
