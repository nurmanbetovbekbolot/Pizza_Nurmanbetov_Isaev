package whz.pti.eva.praktikum_03.service;

import whz.pti.eva.praktikum_03.domain.Item;
import whz.pti.eva.praktikum_03.domain.OrderedItem;
import whz.pti.eva.praktikum_03.dto.CartDTO;
import whz.pti.eva.praktikum_03.dto.CustomerDTO;

import java.util.List;
public interface OrderedItemService {

    List<OrderedItem> listAllOrderedItem();

    boolean addOrderedItem(CartDTO cartDTO, CustomerDTO customerDTO);

    Item getOrderedItemById(String id);

    boolean deleteOrderedItem(String id);
}
