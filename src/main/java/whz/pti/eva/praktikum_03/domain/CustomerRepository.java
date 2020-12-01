package whz.pti.eva.praktikum_03.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import whz.pti.eva.praktikum_03.security.domain.User;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
