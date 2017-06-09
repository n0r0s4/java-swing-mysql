/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proven.customersguiapp.view;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author outsider
 */
public class WelcomePage extends JPanel {
    
    private final String greetings = "Welcome to Customer Manager App";
    public WelcomePage(){
        
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        
        JLabel label = new JLabel(greetings);
        label.setHorizontalAlignment(JLabel.CENTER);
        add(label,BorderLayout.CENTER);
    }
}
