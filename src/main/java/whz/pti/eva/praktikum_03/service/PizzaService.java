package whz.pti.eva.praktikum_03.service;

import whz.pti.eva.praktikum_03.domain.Pizza;

import java.util.List;

/**
 * The interface Pizza service.
 *
 * @author Isaev A. Nurmanbetov B.
 */
public interface PizzaService {
    /**
     * List all pizza list.
     *
     * @return the list
     */
    List<Pizza> listAllPizza();

    /**
     * Add pizza boolean.
     *
     * @param pizza the pizza
     * @return the boolean
     */
    boolean addPizza(Pizza pizza);

    /**
     * Gets pizza by id.
     *
     * @param id the id
     * @return the pizza by id
     */
    Pizza getPizzaById(Long id);

    /**
     * Delete pizza boolean.
     *
     * @param id the id
     * @return the boolean
     */
    boolean deletePizza(Long id);

    /**
     * Find pizza by name pizza.
     *
     * @param name the name
     * @return the pizza
     */
    Pizza findPizzaByName(String name);
}
