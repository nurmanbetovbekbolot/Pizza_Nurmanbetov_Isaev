package whz.pti.eva.praktikum_03.service;

import whz.pti.eva.praktikum_03.domain.Ordered;
import whz.pti.eva.praktikum_03.domain.OrderedItem;
import whz.pti.eva.praktikum_03.dto.CartDTO;
import whz.pti.eva.praktikum_03.dto.CustomerDTO;

import java.util.List;

/**
 * The interface Ordered item service.
 *
 * @author Isaev A. Nurmanbetov B.
 */
public interface OrderedItemService {

    /**
     * List all ordered item list.
     *
     * @return the list
     */
    List<OrderedItem> listAllOrderedItem();

    /**
     * Add ordered item ordered.
     *
     * @param cartDTO     the cart dto
     * @param customerDTO the customer dto
     * @return the ordered
     */
    Ordered addOrderedItem(CartDTO cartDTO, CustomerDTO customerDTO);

    /**
     * Gets ordered item by id.
     *
     * @param id the id
     * @return the ordered item by id
     */
    OrderedItem getOrderedItemById(Long id);

    /**
     * Delete ordered item boolean.
     *
     * @param id the id
     * @return the boolean
     */
    boolean deleteOrderedItem(Long id);
}
