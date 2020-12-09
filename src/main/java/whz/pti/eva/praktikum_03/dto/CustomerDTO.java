package whz.pti.eva.praktikum_03.dto;

import lombok.*;
import whz.pti.eva.praktikum_03.domain.DeliveryAddress;

import java.util.List;

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
    private Boolean isActive;
    private List<DeliveryAddress> deliveryAddresses;

    public CustomerDTO(String id, String firstName, String lastName, String loginName, Boolean isActive) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.loginName = loginName;
        this.isActive = isActive;
    }
}
