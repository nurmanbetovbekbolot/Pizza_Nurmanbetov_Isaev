package whz.pti.eva.praktikum_03.service;

import org.springframework.stereotype.Service;
import whz.pti.eva.praktikum_03.domain.Item;
import whz.pti.eva.praktikum_03.domain.OrderedItem;
import whz.pti.eva.praktikum_03.domain.OrderedItemRepository;

import java.util.List;

@Service
public class OrderedItemServiceImpl implements OrderedItemService{

    private final OrderedItemRepository orderedItemRepository;

    public OrderedItemServiceImpl(OrderedItemRepository orderedItemRepository) {
        this.orderedItemRepository = orderedItemRepository;
    }

    @Override
    public List<OrderedItem> listAllOrderedItem() {
        return orderedItemRepository.findAll();
    }

    @Override
    public boolean addOrderedItem(OrderedItem orderedItem) {
        if(orderedItem==null) return false;

        orderedItemRepository.save(orderedItem);
        return true;
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
