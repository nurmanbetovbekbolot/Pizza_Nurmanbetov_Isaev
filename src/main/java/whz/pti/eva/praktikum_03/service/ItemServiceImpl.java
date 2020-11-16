package whz.pti.eva.praktikum_03.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import whz.pti.eva.praktikum_03.domain.Item;
import whz.pti.eva.praktikum_03.domain.ItemRepository;
import whz.pti.eva.praktikum_03.domain.Pizza;
import whz.pti.eva.praktikum_03.enums.PizzaSize;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    private PizzaService pizzaService;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public List<Item> listAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public boolean addItem(Item item) {
        if (item == null) return false;

        itemRepository.save(item);
        return true;
    }


    @Override
    public boolean addItem(PizzaSize pizzaSize, Integer quantity, String pizzaName) {
        Item item = new Item();
        Pizza pizzaInDb = pizzaService.findPizzaByName(pizzaName);
        item.setQuantity(quantity);
        Pizza pizzaItem = new Pizza();
        pizzaItem.setName(pizzaInDb.getName());
        if (pizzaSize.equals(PizzaSize.SMALL)) {
            pizzaItem.setPriceLarge(pizzaInDb.getPriceSmall());
        } else if (pizzaSize.equals(PizzaSize.MEDIUM)) {
            pizzaItem.setPriceLarge(pizzaInDb.getPriceMedium());
        } else if (pizzaSize.equals(PizzaSize.LARGE)) {
            pizzaItem.setPriceLarge(pizzaInDb.getPriceLarge());
        }
        item.setPizza(pizzaItem);
        item.setPizzaSize(pizzaSize);
        itemRepository.save(item);
        return false;
    }

    @Override
    public Item getItemById(String id) {
        return null;
    }

    @Override
    public boolean deleteItem(String id) {
        return false;
    }
}
