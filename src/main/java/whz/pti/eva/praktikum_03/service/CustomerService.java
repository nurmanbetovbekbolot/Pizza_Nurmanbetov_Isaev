package whz.pti.eva.praktikum_03.service;

import whz.pti.eva.praktikum_03.domain.Customer;
import whz.pti.eva.praktikum_03.dto.CustomerDTO;

public interface CustomerService {

    CustomerDTO findByUserId(String userId);
    Customer updateCustomer(CustomerDTO customerDTO);
    Customer getCustomerById(String customerId);
    Customer saveCustomer(Customer customer);
}
