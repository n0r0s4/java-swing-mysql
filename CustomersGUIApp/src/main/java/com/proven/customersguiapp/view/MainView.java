/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proven.customersguiapp.view;

import com.proven.customersguiapp.controller.Controller;
import com.proven.customersguiapp.model.Customer;
import com.proven.customersguiapp.model.Model;
import java.awt.BorderLayout;
import static java.awt.BorderLayout.CENTER;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import static java.lang.System.exit;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author outsider
 */
public class MainView extends JFrame implements ActionListener {
    private final String title="Customer Manager App";
    private final String file="File";
    private final String exit="Exit";
    private final String edit="Edit";
    private final String find_by_id="Find by id";
    private final String add_new_customer="Add new customer";
    private final String find_by_name="Find by name";
    private final String list_all="List all";
    private final String help="Help";
    private final String about="About";
    private final String option_about="about";
    private final String option_list_all="list_all";
    private final String option_find_by_name="find_by_name_form";
    private final String option_find_by_id="find_by_id_form";
    private final String option_exit="exit";
    private final String option_new_customer="add_new_customer";
    private final String removecustomer = "remove_customer";
    private final String updatecustomer = "update_customer";
    private final String addcustomer = "add_customer";
    private final String confirm_exit="Are you sure you want to exit?";
    private final String confirm_delete="Are you sure you want to remove this customer?";
    private final String confirm_update="Are you sure you want to update this customer?";
    private final String confirm_add="Are you sure you want to insert this customer?";
    private final String about_message="<html><h2>Customers GUI App</h2> <p>norosa@programmer.net</p></html>";
    private final String confirm_removed="Customer removed succesfully";
    private final String confirm_updated="Customer updated succesfully";
    private final String confirm_added="Customer added succesfully";
    private final String cancel_removed="Customer not removed";
    private final String cancel_update="Customer not updated";
    private JTextField id;
    private JTextField name;
    private JTextField year;
    private JTextField phone;
    private final String HEADER_ID="CUSTOMER DATA";
    private final String data_error="Error with data base";
    private final String error_not_added="Error: Customer not added";
    private final String error_data_type="Error: Year must be number";
    private final String error_not_updated="Error: Customer not updated";
    private WelcomePage wp = new WelcomePage();
    private Model model;
    private Controller controller;
    
   /**
    * Constructor
    * @param controller
    * @param model 
    */ 
   public MainView(Controller controller, Model model){
       this.model = model;
       this.controller=controller;
       initComponents();
   }

    /**
     * Starts the application gui.
     */
    private void initComponents() {
       setTitle(title);
       setSize(500,500);
       setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
       addWindowListener(new WindowAdapter(){
          public void windowClosing(WindowEvent we){
              exitApplication();
          } 
       });
       buildMenuBar();
       setContentPane(wp);
       setVisible(true);
    }
    
    /**
     * Builds the menu bar.
     */
    private void buildMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        
        JMenu menu;
        JMenuItem menuItem;
        
        menu = new JMenu(file);
        menuItem = new JMenuItem(exit);
        menuItem.setActionCommand(option_exit);
        menuItem.addActionListener(this);
        //todo
        menu.add(menuItem);
        menuBar.add(menu);
        
        menu = new JMenu(edit);
        //
        menuItem = new JMenuItem(list_all);
        menuItem.setActionCommand(option_list_all);
        menuItem.addActionListener(this);
        menu.add(menuItem);
        //
        menuItem = new JMenuItem(find_by_name);
        menuItem.setActionCommand(option_find_by_name);
        menuItem.addActionListener(this);
        menu.add(menuItem);
        //
        menuItem = new JMenuItem(find_by_id);
        menuItem.setActionCommand(option_find_by_id);
        menuItem.addActionListener(this);
        menu.add(menuItem);
        //
        menuItem = new JMenuItem(add_new_customer);
        menuItem.setActionCommand(option_new_customer);
        menuItem.addActionListener(this);
        menu.add(menuItem);
        //
        menuBar.add(menu);
        
        menu = new JMenu(help);
        //
        menuItem = new JMenuItem(about);
        menuItem.setActionCommand(option_about);
        menuItem.addActionListener(this);
        menu.add(menuItem);
        //
        
        menuBar.add(menu);
        setJMenuBar(menuBar);
    }
    
    /**
     * Process actions from gui
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e){
        String action = e.getActionCommand();
        //System.out.println(action);
        switch(action){
            case option_exit: 
                exitApplication();                 
                break;
                
            case option_list_all:
                controller.listAllCustomers();
                break;
            
            case option_find_by_id:
                controller.findById();
                break;
                
            case option_find_by_name:
                controller.findByName();
                break;
            
            case removecustomer:
                removeCustomer();
                break;
            
            case updatecustomer:
                updateCustomer();
                break;
                
            case option_new_customer:
                addCustomerForm();
                break;
                
            case addcustomer:
                addCustomer();
                break;
                
            case option_about:
                about();
                break;
            
            case "cancel":
                setContentPane(wp);
                break;
                
            default: 
                System.out.println("Incorrect action: "+action);
                break;
        }
    }

    /**
     * Exits the application.
     */
    private void exitApplication() {
        
        int option = JOptionPane.showConfirmDialog(this,confirm_exit);
        if(option == JOptionPane.YES_OPTION)
            System.exit(0);
    }


    /**
     * Shows table with customers when its needed.
     * @param list 
     */
    public void showTable(List<Customer> list) {
        Object[] columnNames = {
            "id", "name" ,"phone", "year"
        };
        DefaultTableModel model = new DefaultTableModel(new Object[0][0], columnNames);
        for(Customer c : list){
          Object[] o = new Object[4];
          o[0] = c.getId();
          o[1] = c.getName();
          o[2] = c.getPhone();
          o[3] = c.getYear();
          model.addRow(o);
        }
       JTable table = new JTable(model);
       JPanel panel = new JPanel();
       panel.add(new JScrollPane(table));
       setContentPane(panel);
       validate();
        
    }

    /**
     * Shows message to inform user.
     * @param retrieving_data_error 
     */
    public void showMessage(String retrieving_data_error) {
        JOptionPane.showMessageDialog(this, retrieving_data_error);
    }

    /**
     * Gets message to retrieve id or name to seek from user.
     * @param message_get_id
     * @return 
     */
    public String input(String message_get_id) {
       return  JOptionPane.showInputDialog(this, message_get_id);       
    }

    /**
     * Shows customer's data when its needed, to update or remove him/her
     * from database.
     * @param c 
     */
    public void showCustomerForm(Customer c) {
        //todo
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(0,2));//colums

        JLabel lbid = new JLabel("Id: ");
        inputPanel.add(lbid);
        id = new JTextField(11);
        inputPanel.add(id);
        id.setText(Long.toString(c.getId()));
        id.setEditable(false);
        
        JLabel lbname = new JLabel("Name: ");
        inputPanel.add(lbname);
        name = new JTextField(20);
        inputPanel.add(name);
        name.setText(c.getName());
        
        JLabel lbphone = new JLabel("Phone: ");
        inputPanel.add(lbphone);
        phone = new JTextField(20);
        inputPanel.add(phone);
        phone.setText(c.getPhone());
        
        JLabel lbyear = new JLabel("Year: ");
        inputPanel.add(lbyear);
        year = new JTextField(20);
        inputPanel.add(year);
        year.setText(Integer.toString(c.getYear()));
        
        JLabel lblank = new JLabel("");
        inputPanel.add(lblank);
        JLabel lblankB = new JLabel("");
        inputPanel.add(lblankB);
        

        JButton btDel = new JButton("Delete");
        btDel.setActionCommand(removecustomer);
        btDel.addActionListener(this);
        inputPanel.add(btDel);
        
        JButton btUpd = new JButton("Update");
        btUpd.setActionCommand(updatecustomer);
        btUpd.addActionListener(this);
        inputPanel.add(btUpd);
        
        JLabel lblankC = new JLabel("");
        inputPanel.add(lblankC);
        JLabel lblankD = new JLabel("");
        inputPanel.add(lblankD);
        
        JButton btCnc = new JButton("Cancel");
        btCnc.setActionCommand("cancel");
        btCnc.addActionListener(this);
        inputPanel.add(btCnc);
        
        JPanel panel = new JPanel();
        panel.add(inputPanel);
        setContentPane(panel);
        validate();
        
        System.out.println(c.toString());
    }

    /**
     * Shows message to confirm remove the customer. After this if confirms,
     * the user will be removed using controller by ID. Otherwise, 
     * doesn't happen.
     */
    private void removeCustomer() {
        int succes=-1;
        int option = JOptionPane.showConfirmDialog(this,confirm_delete);
        if(option == JOptionPane.YES_OPTION){
            succes=controller.removeCustomer(Long.parseLong(id.getText()));
             if(succes!=0){
                 showMessage(confirm_removed);
                 setContentPane(wp);  
             }

             else
                 showMessage(data_error);
        }
        else
            showMessage(cancel_removed);
    }

    /**
     * Shows message to confirm update the customer. After this if confirms,
     * the user will be updated using controller. Otherwise, doesn't happen.
     */
    private void updateCustomer() {
        int succes=-1;
        int option = JOptionPane.showConfirmDialog(this,confirm_update);
        if(option == JOptionPane.YES_OPTION){
          try{  
            succes=controller.updateCustomer(
                    new Customer(Long.parseLong(id.getText()), 
                                name.getText(), 
                                phone.getText(), 
                                Integer.parseInt(year.getText())));
          }catch(NumberFormatException ex){
              showMessage(error_data_type);
          }
             if(succes!=0 && succes!=-1){
                 showMessage(confirm_updated);
                 setContentPane(wp); 
             }
             else
                 showMessage(error_not_updated);
        }
        else
            showMessage(cancel_update);
    }
    
    /**
     * Shows message to confirm add the customer. After this if confirms,
     * the user will be added using controller. Otherwise, doesn't happen.
     */
    private void addCustomer() {
        int succes=-1;
        int option = JOptionPane.showConfirmDialog(this,confirm_add);
        if(option == JOptionPane.YES_OPTION){
          try{  
            succes=controller.addCustomer(
                    new Customer(0, 
                                name.getText(), 
                                phone.getText(), 
                                Integer.parseInt(year.getText())));
          }catch(NumberFormatException ex){
              showMessage(error_data_type);
          }
             if(succes!=0 && succes!=-1){
                 showMessage(confirm_added);
                 setContentPane(wp); 
             }
             else
                 showMessage(error_not_added);
        }
        else
            showMessage(cancel_update);
    }
    
    /**
     * Shows form to add customer.
     */
    private void addCustomerForm(){
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(0,2));
        
        JLabel lbname = new JLabel("Name: ");
        inputPanel.add(lbname);
        name = new JTextField(20);
        inputPanel.add(name);
        
        JLabel lbphone = new JLabel("Phone: ");
        inputPanel.add(lbphone);
        phone = new JTextField(20);
        inputPanel.add(phone);
        
        JLabel lbyear = new JLabel("Year: ");
        inputPanel.add(lbyear);
        year = new JTextField(20);
        inputPanel.add(year);
        
        JLabel lblank = new JLabel("");
        inputPanel.add(lblank);
        JLabel lblankB = new JLabel("");
        inputPanel.add(lblankB);
        
        JButton btAdd = new JButton(add_new_customer);
        btAdd.setActionCommand(addcustomer);
        btAdd.addActionListener(this);
        inputPanel.add(btAdd);

        
        JButton btCnc = new JButton("Cancel");
        btCnc.setActionCommand("cancel");
        btCnc.addActionListener(this);
        inputPanel.add(btCnc);
        
        JPanel panel = new JPanel();
        panel.add(inputPanel);
        setContentPane(panel);
        validate();
        
    }    
    
    /**
     * Shows information message.
     */
    private void about() {
        showMessageDialog(this, about_message);
    }
}
    
  
