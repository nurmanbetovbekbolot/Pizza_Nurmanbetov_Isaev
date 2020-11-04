package whz.pti.eva.praktikum_03.service;

import whz.pti.eva.praktikum_03.domain.Item;
import whz.pti.eva.praktikum_03.domain.OrderedItem;

import java.util.List;
public interface OrderedItemService {

    List<OrderedItem> listAllOrderedItem();

    boolean addOrderedItem(OrderedItem orderedItem);

    Item getOrderedItemById(String id);

    boolean deleteOrderedItem(String id);
}
