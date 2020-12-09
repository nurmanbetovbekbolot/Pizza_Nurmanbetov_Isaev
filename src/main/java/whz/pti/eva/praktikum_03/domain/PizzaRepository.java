package whz.pti.eva.praktikum_03.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Pizza repository.
 *
 * @author Isaev A. Nurmanbetov B.
 */
public interface PizzaRepository extends JpaRepository<Pizza, Long> {
    /**
     * Find by pizza name.
     *
     * @param name the name
     * @return the pizza
     */
    Pizza findByName(String name);

}