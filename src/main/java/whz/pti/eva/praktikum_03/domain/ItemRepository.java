package whz.pti.eva.praktikum_03.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Item repository.
 *
 * @author Isaev A. Nurmanbetov B.
 */
public interface ItemRepository extends JpaRepository<Item, String> {
}
