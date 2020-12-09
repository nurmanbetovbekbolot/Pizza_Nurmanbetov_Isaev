package whz.pti.eva.praktikum_03.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import whz.pti.eva.praktikum_03.domain.Item;
import whz.pti.eva.praktikum_03.domain.Ordered;
import whz.pti.eva.praktikum_03.domain.OrderedItem;
import whz.pti.eva.praktikum_03.domain.OrderedItemRepository;
import whz.pti.eva.praktikum_03.dto.CartDTO;
import whz.pti.eva.praktikum_03.dto.CustomerDTO;
import whz.pti.eva.praktikum_03.security.service.user.UserServiceImpl;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * The class OrderedItemServiceImpl for middleware. All business logic is here
 *
 * @author Isaev A. Nurmanbetov B.
 */
@Service
public class OrderedItemServiceImpl implements OrderedItemService {
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);


    private OrderedItemRepository orderedItemRepository;
    private CustomerService customerService;
    private OrderedService orderedService;

    /**
     * Instantiates a new Ordered item service.
     *
     * @param orderedItemRepository the ordered item repository
     * @param customerService       the customer service
     * @param orderedService        the ordered service
     */
    @Autowired
    public OrderedItemServiceImpl(OrderedItemRepository orderedItemRepository, CustomerService customerService, OrderedService orderedService) {
        this.orderedItemRepository = orderedItemRepository;
        this.customerService = customerService;
        this.orderedService = orderedService;
    }

    @Override
    public List<OrderedItem> listAllOrderedItem() {
        log.debug("Getting all items");
        return orderedItemRepository.findAll();
    }


    //Add OrderedItems to Ordered
    @Override
    public Ordered addOrderedItem(CartDTO cartDTO, CustomerDTO customerDTO) {
        log.debug("Adding orderedItem");

        Ordered ordered = new Ordered();

        Map<String, Item> itemMap = cartDTO.getItems();
        ordered.setNumberOfItems(itemMap.size());
        if (itemMap.size() != 0) {
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

            ordered.setUserId(
                    customerService.getCustomerById(customerDTO.getId()));
//                    customerRepository.getOne(customerDTO.getId()));
            return orderedService.saveOrdered(ordered);
        }
        return null;
    }

    @Override
    public OrderedItem getOrderedItemById(Long id) {
        log.debug("Getting OrderedItem with Id{}", id);
        OrderedItem orderedItem = orderedItemRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException(String.format(">>> OrderedItem not found with id=%s", id)));
        return orderedItem;
    }

    @Override
    public boolean deleteOrderedItem(Long id) {
        log.debug("Deleting OrderedItem with Id{}", id);
        orderedItemRepository.deleteById(id);
        return true;
    }
}
