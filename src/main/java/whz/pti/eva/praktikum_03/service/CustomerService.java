package whz.pti.eva.praktikum_03.service;

import whz.pti.eva.praktikum_03.domain.Customer;
import whz.pti.eva.praktikum_03.dto.CustomerDTO;

/**
 * The interface Customer service.
 *
 * @author Isaev A. Nurmanbetov B.
 */
public interface CustomerService {

    /**
     * Find by user id customer dto.
     *
     * @param userId the user id
     * @return the customer dto
     */
    CustomerDTO findByUserId(String userId);

    /**
     * Update customer customer.
     *
     * @param customerDTO the customer dto
     * @return the customer
     */
    Customer updateCustomer(CustomerDTO customerDTO);

    /**
     * Gets customer by id.
     *
     * @param customerId the customer id
     * @return the customer by id
     */
    Customer getCustomerById(String customerId);

    /**
     * Save customer customer.
     *
     * @param customer the customer
     * @return the customer
     */
    Customer saveCustomer(Customer customer);
}
