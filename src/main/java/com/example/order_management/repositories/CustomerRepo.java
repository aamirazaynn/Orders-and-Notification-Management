package com.example.order_management.repositories;

import com.example.order_management.entities.Customer;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;

@Repository
@Component
public class CustomerRepo {
    private final ArrayList<Customer> customers;

    public CustomerRepo(ArrayList<Customer> customers) {
        this.customers = customers;
    }

    public void addCustomer(Customer customer){
        customers.add(customer);
    }

    public Customer getCustomer(String username){
        for(Customer customer : customers){
            if(customer.getUsername().equals(username)){
                return customer;
            }
        }
        return null;
    }

    public ArrayList<Customer> getAllCustomers(){
        return customers;
    }

    public void removeCustomer(String username){
        customers.removeIf(customer -> customer.getUsername().equals(username));
    }

    public void updateCustomerBalance(Customer customer){
        for(Customer customer1 : customers){
            if(customer1.getUsername().equals(customer.getUsername())){
                customer1.setBalance(customer.getBalance());
            }
        }
    }

    public void printCustomers(){
        for(Customer customer : customers){
            System.out.println(customer.getUsername());
            System.out.println(customer.getLocation());
            System.out.println(customer.getBalance());
            System.out.println(customer.getPhoneNumber());

        }
    }
}
