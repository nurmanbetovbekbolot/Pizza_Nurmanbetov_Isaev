package whz.pti.eva.praktikum_03.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PizzaRepository extends JpaRepository<Pizza, Long> {
    Pizza findByName(String name);

}