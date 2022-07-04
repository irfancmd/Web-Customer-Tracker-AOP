package com.example.springdemo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springdemo.dao.CustomerDAO;
import com.example.springdemo.entity.Customer;

// The "Service" classes work as an extra abstraction between the DAOs and the Controller. It
// may happen that one controller requires multiple DAOs to fetch data. In that case, a service
// object can sit between the controller and DAOs. This service object will be responsible for
// handling transactions. The controller only has to be dependent on this single service object
// instead of multiple DAOs.
@Service
public class CustomerServiceImpl implements CustomerService {
	
	// Injecting CustomerDAO dependency
	@Autowired
	CustomerDAO customerDAO;
	
	@Override
	// If we're using a service layer, the service layer has to manage transactions. Not individual DAOs.
	// So, the "Transactional" annotation will only be used here
	@Transactional
	public List<Customer> getCustomers() {
		return customerDAO.getCustomers();
	}

	@Override
	@Transactional
	public void saveCustomer(Customer customer) {
		customerDAO.saveCustomer(customer);
	}

	@Override
	@Transactional
	public Customer getCustomer(int id) {
		return customerDAO.getCustomer(id);
	}

	@Override
	@Transactional
	public void deleteCustomer(int id) {
		customerDAO.deleteCustomer(id);
		
	}

}
