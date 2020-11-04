package whz.pti.eva.praktikum_03.service;

import org.springframework.stereotype.Service;
import whz.pti.eva.praktikum_03.domain.Item;
import whz.pti.eva.praktikum_03.domain.Ordered;
import whz.pti.eva.praktikum_03.domain.OrderedRepository;

import java.util.List;

@Service
public class OrderedServiceImpl implements OrderedService{

    private final OrderedRepository orderedRepository;

    public OrderedServiceImpl(OrderedRepository orderedRepository) {
        this.orderedRepository = orderedRepository;
    }


    @Override
    public List<Ordered> listAllOrdered() {
        return orderedRepository.findAll();
    }

    @Override
    public boolean addOrdered(Ordered ordered) {
        if(ordered==null) return false;

        orderedRepository.save(ordered);
        return true;
    }

    @Override
    public Item getOrderedById(String id) {
        return null;
    }

    @Override
    public boolean deleteOrdered(String id) {
        return false;
    }
}
