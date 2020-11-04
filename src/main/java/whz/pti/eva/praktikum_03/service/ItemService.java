package whz.pti.eva.praktikum_03.service;

import whz.pti.eva.praktikum_03.domain.Item;

import java.util.List;

public interface ItemService {

    List<Item> listAllItems();

    boolean addItem(Item item);

    Item getItemById(String id);

    boolean deleteItem(String id);
}
