package com.ghoneim.springdemo.dao;

import com.ghoneim.springdemo.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO{

//    Auto wiring the session factory
//    (Dependency injection from the bean defined in the XML file)
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Customer> getCustomers() {

//        get the current session
        Session session = sessionFactory.getCurrentSession();

//        create a query
        Query<Customer> query = session.createQuery("FROM Customer ORDER BY lastName");

//        execute the query and get the result list
        List<Customer> customers = query.getResultList();

//        return the result list
        return customers;
    }

    @Override
    public void saveCustomer(Customer customer) {
//        get the current session
        Session session = sessionFactory.getCurrentSession();

//        save the customer to database
        session.saveOrUpdate(customer);
    }

    @Override
    public Customer getCustomer(int id) {
        Session session = sessionFactory.getCurrentSession();
        Customer customer = session.get(Customer.class, id);
        return customer;
    }

    @Override
    public void deleteCustomer(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("DELETE FROM Customer WHERE id=:customerId");
        query.setParameter("customerId", id);
        query.executeUpdate();
    }
}
