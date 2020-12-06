package whz.pti.eva.praktikum_03.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import whz.pti.eva.praktikum_03.dto.CustomerDTO;
import whz.pti.eva.praktikum_03.dto.UserDTO;
import whz.pti.eva.praktikum_03.security.domain.User;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, String> {

//    Optional<Customer> findByUser(User user);

    Optional<Customer> findCustomerByUserId(String userId);


//    @Query(value = "select  c.id,c.first_name,c.last_name,c.login_name,c.password_hash,c.is_active  from customer c inner join secuser sc on c.user_id = sc.id where c.user_id = :secuser_id", nativeQuery = true)
//    Customer findCustomerByUser(@Param("secuser_id") String userId);

//    @Query("select new whz.pti.eva.praktikum_03.dto.CustomerDTO(c.id,c.firstName,c.lastName,c.loginName,c.passwordHash,c.isActive) FROM Customer c where c.user.id = :id")
//    Optional<CustomerDTO> findCustomerByUser(@Param("id") String id);
}