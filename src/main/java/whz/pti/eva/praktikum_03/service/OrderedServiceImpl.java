package whz.pti.eva.praktikum_03.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import whz.pti.eva.praktikum_03.domain.Customer;
import whz.pti.eva.praktikum_03.domain.Item;
import whz.pti.eva.praktikum_03.domain.Ordered;
import whz.pti.eva.praktikum_03.domain.OrderedRepository;

import java.util.List;

@Service
public class OrderedServiceImpl implements OrderedService {

    private OrderedRepository orderedRepository;

    private CustomerService customerService;

    @Autowired
    public OrderedServiceImpl(OrderedRepository orderedRepository, CustomerService customerService) {
        this.orderedRepository = orderedRepository;
        this.customerService = customerService;
    }

    @Override
    public List<Ordered> listAllOrdered() {
        return orderedRepository.findAll();
    }

    @Override
    public boolean addOrdered(Ordered ordered) {
        if (ordered == null) return false;

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


    @Override
    public Ordered saveOrdered(Ordered ordered) {
        return orderedRepository.save(ordered);
    }

    @Override
    public List<Ordered> findAllByCustomerId(String customerId) {
        Customer customer = customerService.getCustomerById(customerId);
        List<Ordered> orderedList = orderedRepository.findAllByUserId(customer);
        return orderedList;
    }
}
