package whz.pti.eva.praktikum_03.service;

import whz.pti.eva.praktikum_03.domain.Item;
import whz.pti.eva.praktikum_03.domain.Pizza;

import java.util.List;

public interface PizzaService {
    List<Pizza> listAllPizza();

    boolean addPizza(Pizza pizza);

    Item getPizzaById(String id);

    boolean deletePizza(String id);

    Pizza findPizzaByName(String name);
}
