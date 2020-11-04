package whz.pti.eva.praktikum_03.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderedItemRepository extends JpaRepository<OrderedItem, Long> {
}
