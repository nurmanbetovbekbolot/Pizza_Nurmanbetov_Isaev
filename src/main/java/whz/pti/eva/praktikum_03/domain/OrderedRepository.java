package whz.pti.eva.praktikum_03.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderedRepository extends JpaRepository<Ordered,Long> {
}
