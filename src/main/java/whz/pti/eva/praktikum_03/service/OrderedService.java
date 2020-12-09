package whz.pti.eva.praktikum_03.service;

import whz.pti.eva.praktikum_03.domain.Ordered;

import java.util.List;

/**
 * The interface Ordered service.
 *
 * @author Isaev A. Nurmanbetov B.
 */
public interface OrderedService {

    /**
     * List all ordered list.
     *
     * @return the list
     */
    List<Ordered> listAllOrdered();

    /**
     * Add ordered boolean.
     *
     * @param ordered the ordered
     * @return the boolean
     */
    boolean addOrdered(Ordered ordered);

    /**
     * Gets ordered by id.
     *
     * @param id the id
     * @return the ordered by id
     */
    Ordered getOrderedById(Long id);

    /**
     * Delete ordered boolean.
     *
     * @param id the id
     * @return the boolean
     */
    boolean deleteOrdered(Long id);

    /**
     * Find all by customer id list.
     *
     * @param customerId the customer id
     * @return the list
     */
    List<Ordered> findAllByCustomerId(String customerId);

    /**
     * Save ordered ordered.
     *
     * @param ordered the ordered
     * @return the ordered
     */
    Ordered saveOrdered (Ordered ordered);
}
