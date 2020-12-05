package whz.pti.eva.praktikum_03.service;

import whz.pti.eva.praktikum_03.domain.Customer;
import whz.pti.eva.praktikum_03.domain.CustomerRepository;

import java.util.Optional;

public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

//    @Override
//    public Customer findCustomerByUser(Long id) {
//        Optional<Customer> customer = customerRepository.findCustomerByUser(id);
//        return customer.orElse(new Customer());
//    }
}
