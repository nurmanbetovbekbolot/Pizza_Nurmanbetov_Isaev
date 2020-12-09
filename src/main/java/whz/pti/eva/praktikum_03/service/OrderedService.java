package whz.pti.eva.praktikum_03.service;

import whz.pti.eva.praktikum_03.domain.Item;
import whz.pti.eva.praktikum_03.domain.Ordered;

import java.util.List;

public interface OrderedService {

    List<Ordered> listAllOrdered();

    boolean addOrdered(Ordered ordered);

    Item getOrderedById(String id);

    boolean deleteOrdered(String id);

//    List<Ordered> findAllByCustomerId(String customerId);
}
