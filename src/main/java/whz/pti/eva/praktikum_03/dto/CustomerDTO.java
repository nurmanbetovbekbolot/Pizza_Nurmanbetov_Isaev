package whz.pti.eva.praktikum_03.dto;

import lombok.*;
import whz.pti.eva.praktikum_03.domain.DeliveryAddress;

import java.util.List;

/**
 * The class Customer dto.
 *
 * @author Isaev A. Nurmanbetov B.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String loginName;
    private String passwordHash;
    private String user;
    private Boolean active;
    private List<DeliveryAddress> deliveryAddresses;

    /**
     * Instantiates a new Customer dto.
     *
     * @param id        the id
     * @param firstName the first name
     * @param lastName  the last name
     * @param loginName the login name
     * @param active    the is active
     */
    public CustomerDTO(String id, String firstName, String lastName, String loginName, Boolean active) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.loginName = loginName;
        this.active = active;
    }
}
