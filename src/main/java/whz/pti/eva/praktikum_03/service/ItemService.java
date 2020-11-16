package whz.pti.eva.praktikum_03.service;

import whz.pti.eva.praktikum_03.domain.Item;
import whz.pti.eva.praktikum_03.enums.PizzaSize;

import java.util.List;

public interface ItemService {

    List<Item> listAllItems();

    boolean addItem(Item item);

    boolean addItem(PizzaSize pizzaSize, Integer amount, String pizza);

    Item getItemById(String id);

    boolean deleteItem(String id);
}
