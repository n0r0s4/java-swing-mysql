/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proven.customersguiapp;

import com.proven.customersguiapp.controller.Controller;
import com.proven.customersguiapp.model.Model;
import javax.swing.SwingUtilities;

/**
 *
 * @author outsider
 */
public class CustomerGuiApp {
    
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable(){
        public void run(){
            Model model = new Model();
            Controller controller = new Controller(model);
        }
    });
  }
    
}
