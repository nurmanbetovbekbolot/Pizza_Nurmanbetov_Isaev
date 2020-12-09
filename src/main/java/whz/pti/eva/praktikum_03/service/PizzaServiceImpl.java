package whz.pti.eva.praktikum_03.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import whz.pti.eva.praktikum_03.domain.Item;
import whz.pti.eva.praktikum_03.domain.Pizza;
import whz.pti.eva.praktikum_03.domain.PizzaRepository;

import java.util.List;

@Service
public class PizzaServiceImpl implements PizzaService {

    private PizzaRepository pizzaRepository;

    @Autowired
    public PizzaServiceImpl(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    @Override
    public List<Pizza> listAllPizza() {
        return pizzaRepository.findAll();
    }

    @Override
    public boolean addPizza(Pizza pizza) {
        if (pizza == null) return false;
        pizzaRepository.save(pizza);
        return true;
    }

    @Override
    public Item getPizzaById(String id) {
        return null;
    }

    @Override
    public boolean deletePizza(String id) {
        return false;
    }

    @Override
    public Pizza findPizzaByName(String name) {
        return pizzaRepository.findByName(name);
    }
}
