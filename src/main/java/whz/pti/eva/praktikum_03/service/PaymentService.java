package whz.pti.eva.praktikum_03.service;


import whz.pti.eva.praktikum_03.dto.PayActionResponseDTO;

/**
 * The interface Payment service.
 *
 * @author Isaev A. Nurmanbetov B.
 */
public interface PaymentService {
    /**
     * Method doPayAction to transfer money from one account to another.
     *
     * @param from     the from
     * @param to       the to
     * @param pcontent the pcontent
     * @return payActionResponseDTO
     */
    PayActionResponseDTO doPayAction(String from, String to, String pcontent);
}
