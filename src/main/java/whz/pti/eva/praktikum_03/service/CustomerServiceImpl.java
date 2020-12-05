package whz.pti.eva.praktikum_03.service;

import org.springframework.stereotype.Service;
import whz.pti.eva.praktikum_03.domain.Customer;
import whz.pti.eva.praktikum_03.domain.CustomerRepository;
import whz.pti.eva.praktikum_03.security.domain.User;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @Override
    public Customer findByUser(User user) {
        Optional<Customer> customer = customerRepository.findByUser(user);
        return customer.orElse(new Customer());
    }
}
