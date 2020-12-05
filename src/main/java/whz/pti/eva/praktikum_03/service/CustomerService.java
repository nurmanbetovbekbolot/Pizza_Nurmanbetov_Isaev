package whz.pti.eva.praktikum_03.service;

import whz.pti.eva.praktikum_03.domain.Customer;
import whz.pti.eva.praktikum_03.security.domain.User;

import java.util.Optional;

public interface CustomerService {

    Customer findByUser(User user);
}
