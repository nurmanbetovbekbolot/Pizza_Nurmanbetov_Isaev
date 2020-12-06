package whz.pti.eva.praktikum_03.service;

import whz.pti.eva.praktikum_03.domain.Customer;
import whz.pti.eva.praktikum_03.dto.CustomerDTO;
import whz.pti.eva.praktikum_03.dto.UserDTO;
import whz.pti.eva.praktikum_03.security.domain.User;

import java.util.Optional;

public interface CustomerService {

//    CustomerDTO findByUser(User user);
    CustomerDTO findByUserId(String userId);
    Customer updateCustomer(CustomerDTO customerDTO);
}
