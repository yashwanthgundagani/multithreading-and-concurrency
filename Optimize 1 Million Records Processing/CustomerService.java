package com.example.processor;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    public List<Customer> generateCustomer(int count){
        List<Customer> customers=new ArrayList<>();
        for(int i=0;i<count;i++){
            customers.add(new Customer(i,"Customer"+i,"Customer"+i+"@gmail.com"));
        }
        return customers;
    }
}
