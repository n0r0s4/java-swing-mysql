package com.proven.customersguiapp.model;

import java.util.Objects;

/**
 * @author outsider
 */
public class Customer {
    private long   id;
    private String name;
    private String phone;
    private int    year;

    public Customer() {
    }

    public Customer(String name, String phone, int age) {
        this.name = name;
        this.phone = phone;
        this.year = age;
    }

    public Customer(long id, String name, String phone, int age) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.year = age;
    }

    public Customer(long id) {
        this.id = id;
    }
    
    public Customer(Customer other) {
        this.id = other.id;
        this.name = other.name;
        this.phone = other.phone;
        this.year = other.year;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.phone);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Customer other = (Customer) obj;
        if (!Objects.equals(this.phone, other.phone)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Customer "+id+" ->" + " Name=" + name + ", Phone=" + phone + ", Year=" + year + '#';
    } 
    
}
