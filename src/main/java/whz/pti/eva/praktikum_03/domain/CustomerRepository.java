package whz.pti.eva.praktikum_03.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import whz.pti.eva.praktikum_03.security.domain.User;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer,String> {

    Customer findByUser(User user);
}