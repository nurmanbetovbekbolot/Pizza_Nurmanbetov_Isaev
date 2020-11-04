package whz.pti.eva.praktikum_03.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import whz.pti.eva.praktikum_03.domain.*;
import whz.pti.eva.praktikum_03.enums.PizzaSize;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;

@Component
public class InitializeDB {

    @Autowired
    CartRepository cartRepository;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    OrderedRepository orderedRepository;
    @Autowired
    OrderedItemRepository orderedItemRepository;
    @Autowired
    PizzaRepository pizzaRepository;


    @Transactional
    @PostConstruct
    public void init() {
        Pizza pizzaMargherita = new Pizza();
        pizzaMargherita.setName("Margherita");
        pizzaMargherita.setPriceSmall(new BigDecimal("5.0"));
        pizzaMargherita.setPriceMedium(new BigDecimal("8.0"));
        pizzaMargherita.setPriceLarge(new BigDecimal("10.0"));
        pizzaRepository.save(pizzaMargherita);
        Pizza pizzaHawai = new Pizza();
        pizzaHawai.setName("Hawai");
        pizzaHawai.setPriceSmall(new BigDecimal("5.0"));
        pizzaHawai.setPriceMedium(new BigDecimal("8.0"));
        pizzaHawai.setPriceLarge(new BigDecimal("10.0"));
        pizzaRepository.save(pizzaHawai);

        Item item1 = new Item();
        item1.setItemId("1");
        item1.setPizza(pizzaMargherita);
        item1.setQuantity(2);
        item1.setSize(PizzaSize.LARGE);
        itemRepository.save(item1);

        OrderedItem orderedItem = new OrderedItem();
        orderedItem.setName(pizzaHawai.getName());
        orderedItem.setPizzaId(pizzaHawai.getId());
        orderedItem.setQuantity(5);
        orderedItem.setUserId("Atai");
        orderedItemRepository.save(orderedItem);

        OrderedItem orderedItem2 = new OrderedItem();
        orderedItem2.setName(pizzaMargherita.getName());
        orderedItem2.setPizzaId(pizzaMargherita.getId());
        orderedItem2.setQuantity(2);
        orderedItem2.setUserId("Atai");
        orderedItemRepository.save(orderedItem2);

        ArrayList<OrderedItem> orderedItems = new ArrayList<>();
        orderedItems.add(orderedItem);
        orderedItems.add(orderedItem2);

        Ordered order1 = new Ordered();
        order1.setItems(orderedItems);
        order1.setNumberOfItems(10);
        orderedRepository.save(order1);
    }


}