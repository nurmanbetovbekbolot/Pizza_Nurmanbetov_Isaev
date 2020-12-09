package whz.pti.eva.praktikum_03.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import whz.pti.eva.praktikum_03.domain.*;

import java.util.List;
import java.util.Optional;

@Service
public class OrderedServiceImpl implements OrderedService{

    private final OrderedRepository orderedRepository;


    @Autowired
    private CustomerRepository customerRepository;

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


//    @Override
//    public List<Ordered> findAllByCustomerId(String customerId) {
//        Optional<Customer> customer = customerRepository.findById(customerId);
//
//        if (customer.isPresent()){
//
//
//        List<Ordered> orderedList = orderedRepository.findAllByUserId(customer.get());
//
//            return orderedList;
//        }
//        return null;
//    }
}
