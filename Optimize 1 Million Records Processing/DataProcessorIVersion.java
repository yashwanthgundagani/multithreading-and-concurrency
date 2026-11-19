package com.example.processor;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class DataProcessorIVersion {
    private static final int THREAD_COUNT=20;
    public void process(List<Customer> customers){
        long start = System.currentTimeMillis();

        ExecutorService executor= Executors.newFixedThreadPool(THREAD_COUNT);
        int chunkSize = customers.size()/THREAD_COUNT;

        List<Callable<Void>> tasks = new ArrayList<>();

        for(int i=0;i< THREAD_COUNT;i++){
            int fromIndex = i * chunkSize;
            int toIndex = (i== THREAD_COUNT -1) ? customers.size() : (i+1) * chunkSize;
            List<Customer> subList = customers.subList(fromIndex,toIndex);

            tasks.add(()->{
                for(Customer customer: subList){
                    transform(customer);
                }
                return null;
            });
        }

        try{
            executor.invokeAll(tasks);
        } catch(InterruptedException e){
            System.out.println(e.getMessage());
        } finally {
            executor.shutdown();
        }

        long end=System.currentTimeMillis();
        System.out.println("Time Taken with multithreading: "+(end-start)+" ms");
    }

    private void transform(Customer customer){
        //mask email and uppercase name
        String maskedEmail=customer.getEmail().replaceAll("(?<=.).(?=.*@)","*");
        customer.setEmail(maskedEmail);
        customer.setName(customer.getName().toUpperCase());
    }
}
