package com.proven.customersguiapp.model.persist;

import com.proven.customersguiapp.model.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomerDAO {
    
    private final String QUERY_FIND_BY_ID = "select * from customers "
            + "where id=?";
    private final String QUERY_FIND_ALL = "select * from customers";
    private final String QUERY_INSERT ="insert into customers "
            + "values (null,?,?,?)";
    private final String QUERY_DELETE ="delete from customers where id=?";
    private final String QUERY_FIND_LIKE_NAME="select * from customers "
            + "where name like ?";
    private final String QUERY_UPDATE="update customers "
            + "set name = ?, phone = ?,"
            + " year = ? where id = ?";
    /**
     * retrieves a customer with a given id.
     * @param id customer id to find
     * @return customer with given id or nul if not found.
     */
    public Customer findById(long id) {
        Customer f=null;       
        try {
            DBConnect db = DBConnect.getInstance();
            Connection conn=db.getConnection();
            if ( conn != null ) {
                PreparedStatement st = conn.prepareStatement(QUERY_FIND_BY_ID);
                st.setLong(1, id);
                ResultSet rs = st.executeQuery();
                rs.next();//si solo se recibe uno, siempre rs.next();
                f = fromResultSet(rs);
            }
        } catch (SQLException | ClassNotFoundException e ) {
            f=null;
        }
        return f;
    }
    
    /**
     * Finds customers with a given name and returns a list (empty is customers 
     * not found) 
     * @param name to find
     * @return customers list
     */
    public List<Customer> findByName(String name) {
        List<Customer> customers=new ArrayList<>();
         try {
            DBConnect db = DBConnect.getInstance();
            Connection conn=db.getConnection();
            if ( conn != null ) {
                PreparedStatement st = conn.prepareStatement(
                        QUERY_FIND_LIKE_NAME);
                st.setString(1, "%"+name+"%");
                ResultSet rs = st.executeQuery();            
                while(rs.next()){
                    customers.add(fromResultSet(rs));
                }
                
            }
        } catch (SQLException | ClassNotFoundException e ) {
            customers = null;
        }
        return customers;
    }
    
   /**
    * Inserts customer in database
    * @param c customer to insert
    * @return result, database output rowsaffected
    */
    public int insert (Customer c) {
        int result = 0;
        try {
            
            DBConnect dataSource = DBConnect.getInstance();
            Connection conn = dataSource.getConnection();
            //sql
            PreparedStatement ps = conn.prepareStatement(QUERY_INSERT);
            ps.setString(1, c.getName());
            ps.setString(2, c.getPhone()); ps.setInt(3, c.getYear());

           result = ps.executeUpdate();
                       
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(
                    Level.SEVERE, null, ex);
        }catch (SQLException ex) {
                Logger.getLogger(CustomerDAO.class.getName()).log(
                        Level.SEVERE, null, ex);
            }
               
        return result;
    }
    
    /**
     * Updates customer in database
     * @param c customer to update with new data
     * @return result, database output rowsaffected
     */
    public int update (Customer c) {
        int result = 0;
        try {
            
            DBConnect dataSource = DBConnect.getInstance();
            Connection conn = dataSource.getConnection();
            //sql name = ?, phone = ?, year = ?
            PreparedStatement ps = conn.prepareStatement(QUERY_UPDATE);
            ps.setLong(4, c.getId()); ps.setString(1, c.getName());
            ps.setString(2, c.getPhone());
            ps.setInt(3, c.getYear());

           result = ps.executeUpdate();
                       
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(
                    Level.SEVERE, null, ex);
        }catch (SQLException ex) {
                Logger.getLogger(CustomerDAO.class.getName()).log(
                        Level.SEVERE, null, ex);
            }
                
        return result;
    }
    
    /**
     * Deletes customer in database from given id
     * @param id customers' id to remove
     * @return result, database output, rowsaffected 
     */
    public int delete (Long id) {
        int result = 0;
        try {
            
            DBConnect dataSource = DBConnect.getInstance();
            Connection conn = dataSource.getConnection();
            //sql
            PreparedStatement ps = conn.prepareStatement(QUERY_DELETE);
            ps.setLong(1, id);

           result = ps.executeUpdate();
                       
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(
                    Level.SEVERE, null, ex);
        }catch (SQLException ex) {
                Logger.getLogger(CustomerDAO.class.getName()).log(
                        Level.SEVERE, null, ex);
            }
               
        return result;
    }

    /**
     * retrieves data from current row
     * @param rs resultset
     * @return customer or null in case of error
     * @throws SQLException 
     */
    private Customer fromResultSet(ResultSet rs) throws SQLException {
        Customer s = null;
        //TODO
        String name  = rs.getString("name");
        String phone  = rs.getString("phone"); 
        int year = rs.getInt("year");
        long id = rs.getLong("id");
        
        s = new Customer(id,name,phone,year);
                
        return s;
    }
    
    /**
     * retrieves all customers from data base
     * @return a list of customers or null in case error
     */
    public List<Customer> findAll() {
         List<Customer> list= new ArrayList<Customer>();
         Customer c = null;
        try {
            DBConnect db = DBConnect.getInstance();
            Connection conn=db.getConnection();
            if ( conn != null ) {
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(QUERY_FIND_ALL);
                while(rs.next()){
                    c = fromResultSet(rs);
                    if(c!=null) list.add(c);
                }
                
            }
        } catch (SQLException | ClassNotFoundException e ) {
            System.err.println(e);
            list = null;
        }
        return list;
    }
     
}
