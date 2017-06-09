/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proven.customersguiapp.controller;

import com.proven.customersguiapp.model.Customer;
import com.proven.customersguiapp.model.Model;
import com.proven.customersguiapp.view.MainView;
import java.util.List;

/**
 * 
 * @author outsider
 */
public class Controller {
    private Model model;
    private MainView view;
    
    private final String option_about="about";
    private final String option_list_all="list_all";
    private final String option_find_by_name="find_by_name_form";
    private final String option_find_by_id="find_by_id_form";
    private final String option_exit="exit";
    private final String message_get_id="Insert id: ";
    private final String message_get_name="Insert name: ";
    private final String option_cancelled="option aborted";
    private final String retrieving_data_error="Error retrieving data";
    private final String not_found_error="Customer not found";
    private final String not_found_name="Customers not found with this name";
    private final String incorrect_data_error="Insert correct data";
    
    public Controller(Model model){
        this.model = model;
        view = new MainView(this,model);
    }

    /**
     * Shows customer's list, if list is empty shows advice message,
     * if list is null shows error message.
     */
    public void listAllCustomers() {
        List<Customer> list = model.findAllCustomers();
        if(list != null){
            if(list.isEmpty())
                view.showMessage(not_found_error);
            else
               view.showTable(list);
        }
        else
            view.showMessage(retrieving_data_error);             
    }

    /**
     * Finds customer by id and later the user is able to modify
     * or delete this user, if user doesn't exist, shows and advice.
     */
    public void findById() {
        String answer = view.input(message_get_id);
        if (answer != null){
            try{
                long id = Long.parseLong(answer);
                Customer c = model.findById(id);
                if(c!=null)
                    view.showCustomerForm(c);
                else
                    view.showMessage(not_found_error);
            }catch(NumberFormatException nfe){
                view.showMessage("ID must be number");
            }
        }
    }
    
    /**
     * Finds customer by name (using like '%n%') and shows a list, if list
     * is empty shows and advice, and if list is null shows an error message.
     */
     public void findByName() {
         String name = view.input(message_get_name);
          if (name != null){
            try{
                List<Customer> list = model.findByName(name);
                if(list!=null)
                    if(list.isEmpty())
                        view.showMessage(not_found_name);
                    else
                        view.showTable(list);
                else
                    view.showMessage(not_found_error);
            }catch(Exception e){
                view.showMessage(incorrect_data_error);
            }
        }       
    }
     
     /**
      * Removes customer from a given id
      * @param id
      * @return 1 if succes, 0 otherwise
      */
      public int removeCustomer(long id) {
        return model.deleteCustomer(id);
    }

     /**
      * Updates customer from a given id
      * @param customer
      * @return 1 if succes, 0 otherwise
      */
      public int updateCustomer(Customer customer) {
        return model.updateCustomer(customer);        
    }

    /**
     * Adds customer to database from a given customer
     * @param customer
     * @return 1 if succes, 0 otherwise
     */
    public int addCustomer(Customer customer) {
        return model.addCustomer(customer);
    }

}
