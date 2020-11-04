package whz.pti.eva.praktikum_03.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import whz.pti.eva.praktikum_03.domain.Item;
import whz.pti.eva.praktikum_03.domain.ItemRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public List<Item> listAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public boolean addItem(Item item) {
        if(item==null) return false;

        itemRepository.save(item);
        return true;
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
