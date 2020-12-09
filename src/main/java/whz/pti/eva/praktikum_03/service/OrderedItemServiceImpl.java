package whz.pti.eva.praktikum_03.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import whz.pti.eva.praktikum_03.domain.*;
import whz.pti.eva.praktikum_03.dto.CartDTO;
import whz.pti.eva.praktikum_03.dto.CustomerDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class OrderedItemServiceImpl implements OrderedItemService{

    private final OrderedItemRepository orderedItemRepository;

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private OrderedRepository orderedRepository;

    public OrderedItemServiceImpl(OrderedItemRepository orderedItemRepository) {
        this.orderedItemRepository = orderedItemRepository;
    }

    @Override
    public List<OrderedItem> listAllOrderedItem() {
        return orderedItemRepository.findAll();
    }


    //Add OrderedItems to Ordered
    @Override
    public Ordered addOrderedItem(CartDTO cartDTO, CustomerDTO customerDTO) {

        Ordered ordered = new Ordered();

        Map<String, Item> itemMap = cartDTO.getItems();
        ordered.setNumberOfItems(itemMap.size());
        if (itemMap.size()!=0){
            for (Map.Entry<String, Item> entry : itemMap.entrySet()) {
                OrderedItem orderedItem = new OrderedItem();
                orderedItem.setName(entry.getValue().getPizza().getName());
                orderedItem.setQuantity(entry.getValue().getQuantity());
                orderedItem.setSize(entry.getValue().getPizzaSize());
                orderedItem.setPizzaId(entry.getValue().getPizza().getId());
                orderedItem.setUserId(customerDTO.getId());
                orderedItemRepository.save(orderedItem);
                ordered.getItems().add(orderedItem);
            }

            ordered.setUserId(customerRepository.getOne(customerDTO.getId()));
            return orderedRepository.save(ordered);
        }
        return null;
    }

    @Override
    public Item getOrderedItemById(String id) {
        return null;
    }

    @Override
    public boolean deleteOrderedItem(String id) {
        return false;
    }
}
