package whz.pti.eva.praktikum_03.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import whz.pti.eva.praktikum_03.domain.*;
import whz.pti.eva.praktikum_03.security.service.user.UserServiceImpl;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * The class OrderedServiceImpl for middleware. All business logic is here
 *
 * @author Isaev A. Nurmanbetov B.
 */
@Service
public class OrderedServiceImpl implements OrderedService {
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);


    private OrderedRepository orderedRepository;

    private CustomerService customerService;

    /**
     * Instantiates a new Ordered service.
     *
     * @param orderedRepository the ordered repository
     * @param customerService   the customer service
     */
    @Autowired
    public OrderedServiceImpl(OrderedRepository orderedRepository, CustomerService customerService) {
        this.orderedRepository = orderedRepository;
        this.customerService = customerService;
    }

    @Override
    public List<Ordered> listAllOrdered() {
        log.debug("Getting all ordered list");
        return orderedRepository.findAll();
    }

    @Override
    public boolean addOrdered(Ordered ordered) {
        log.debug("Adding ordered");
        if (ordered == null) return false;
        orderedRepository.save(ordered);
        return true;
    }

    @Override
    public Ordered getOrderedById(Long id) {
        log.debug("Getting ordered withId{}", id);
        Ordered ordered = orderedRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException(String.format(">>> OrderedItem not found with id=%s", id)));
        return ordered;
    }

    @Override
    public boolean deleteOrdered(Long id) {
        log.debug("Deleting ordered withId{}", id);
        orderedRepository.deleteById(id);
        return true;
    }


    @Override
    public Ordered saveOrdered(Ordered ordered) {
        log.debug("Saving ordered");
        return orderedRepository.save(ordered);
    }

    @Override
    public List<Ordered> findAllByCustomerId(String customerId) {
        log.debug("Finding all ordered with CustomerId{}", customerId);
        Customer customer = customerService.getCustomerById(customerId);
        List<Ordered> orderedList = orderedRepository.findAllByUserId(customer);
        return orderedList;
    }
}
