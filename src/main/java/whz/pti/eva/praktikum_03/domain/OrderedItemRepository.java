package whz.pti.eva.praktikum_03.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Ordered item repository.
 *
 * @author Isaev A. Nurmanbetov B.
 */
public interface OrderedItemRepository extends JpaRepository<OrderedItem, Long> {
}
