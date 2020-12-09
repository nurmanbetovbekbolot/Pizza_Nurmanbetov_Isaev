package whz.pti.eva.praktikum_03.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import whz.pti.eva.praktikum_03.domain.Pizza;
import whz.pti.eva.praktikum_03.domain.PizzaRepository;
import whz.pti.eva.praktikum_03.security.service.user.UserServiceImpl;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * The type Pizza service.
 *
 * @author Isaev A. Nurmanbetov B.
 */
@Service
public class PizzaServiceImpl implements PizzaService {
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);


    private PizzaRepository pizzaRepository;

    /**
     * Instantiates a new Pizza service.
     *
     * @param pizzaRepository the pizza repository
     */
    @Autowired
    public PizzaServiceImpl(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    @Override
    public List<Pizza> listAllPizza() {
        log.debug("Getting all pizza list");
        return pizzaRepository.findAll();
    }

    @Override
    public boolean addPizza(Pizza pizza) {
        log.debug("Adding pizza");
        if (pizza == null) return false;
        pizzaRepository.save(pizza);
        return true;
    }

    @Override
    public Pizza getPizzaById(Long id) {
        log.debug("Getting pizza withId{}", id);
        Pizza pizza = pizzaRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException(String.format(">>> Pizza not found with id=%s", id)));
        return pizza;
    }

    @Override
    public boolean deletePizza(Long id) {
        log.debug("Deleting pizza withId{}", id);
        pizzaRepository.deleteById(id);
        return true;
    }

    @Override
    public Pizza findPizzaByName(String name) {
        log.debug("Finding pizza withName{}", name);
        return pizzaRepository.findByName(name);
    }
}
