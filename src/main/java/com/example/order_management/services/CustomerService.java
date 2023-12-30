package com.example.order_management.services;

import com.example.order_management.entities.Customer;
import com.example.order_management.repositories.CustomerRepo;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class CustomerService {
    private final CustomerRepo CustomerRepo;
    public CustomerService(CustomerRepo CustomerRepo) {
        this.CustomerRepo = CustomerRepo;
    }
    public Boolean addCustomer(Customer customer) {
        try {
                if(CustomerRepo.getCustomer(customer.getUsername()) != null){
                return false;
            }
            CustomerRepo.addCustomer(customer);
        } catch (Exception e) {
            System.out.println("Exception in addCustomer as" + e.getMessage());
            return false;
        }
        return true;
    }
    public Customer getCustomerByUsername(String username) {
        try {
            if(CustomerRepo.getCustomer(username) == null){
                return null;
            }
            return CustomerRepo.getCustomer(username);
        } catch (Exception e) {
            System.out.println("Exception in getCustomerByUsername as" + e.getMessage());
            return null;
        }
    }
    public ArrayList<Customer> getAllCustomers() {
        try {
            return CustomerRepo.getAllCustomers();
        } catch (Exception e) {
            System.out.println("Exception in getAllCustomers as" + e.getMessage());
        }
        return null;
    }
    public void updateCustomerBalance(Customer customer) {
        try {
            CustomerRepo.updateCustomerBalance(customer);
        } catch (Exception e) {
            System.out.println("Exception in updateCustomerBalance as" + e.getMessage());
        }
    }
}
