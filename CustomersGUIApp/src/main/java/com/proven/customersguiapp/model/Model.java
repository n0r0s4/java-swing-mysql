package com.proven.customersguiapp.model;

import com.proven.customersguiapp.model.persist.CustomerDAO;
import java.util.List;

/**
 * 
 * @author outsider
 */
public class Model {
    
    /**
     * retrieves the customer with a given id.
     * @param id to search for
     * @return customer with given id of null if not found.
     */
    public Customer findById (long id) {
        CustomerDAO sd = new CustomerDAO();
        return sd.findById(id);
    }
    
    /**
     * retrieves customers list with a given name.
     * @param name to search for
     * @return customers with given name or empty list if not found.
     */
    public List<Customer> findByName (String name) {
        CustomerDAO sd = new CustomerDAO();
        return sd.findByName(name);
    }
    
    /**
     * adds customer using dao
     * @param customer customer to add
     * @return result, 1 if succes, 0 error
     */
    public int addCustomer (Customer customer) {
        //TODO
        CustomerDAO sd = new CustomerDAO();
        int result=sd.insert(customer);
        return result;
    }
    
    /**
     * updates customer using dao
     * @param customer customer to update
     * @return result, 1 if succes, 0 error
     */
    public int updateCustomer (Customer customer) {
        //TODO
        CustomerDAO sd = new CustomerDAO();
        int result=sd.update(customer);
        return result;
    }
    
     /**
     * deletes customer using dao
     * @param id id to seek
     * @return result, 1 if succes, 0 error
     */
    public int deleteCustomer (Long id) {
        //TODO
        CustomerDAO sd = new CustomerDAO();
        int result=sd.delete(id);
        return result;
    }

   /**
     * gets all customers list from db
     * @return list of all customers or null
     */
    public List<Customer> findAllCustomers() {
        CustomerDAO sd = new CustomerDAO();
        return sd.findAll();
    }
    
}//end of class
