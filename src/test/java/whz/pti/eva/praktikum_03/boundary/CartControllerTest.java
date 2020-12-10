package whz.pti.eva.praktikum_03.boundary;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import whz.pti.eva.praktikum_03.domain.Customer;
import whz.pti.eva.praktikum_03.domain.DeliveryAddress;
import whz.pti.eva.praktikum_03.domain.Item;
import whz.pti.eva.praktikum_03.domain.Pizza;
import whz.pti.eva.praktikum_03.dto.CartDTO;
import whz.pti.eva.praktikum_03.dto.CustomerDTO;
import whz.pti.eva.praktikum_03.enums.PizzaSize;
import whz.pti.eva.praktikum_03.enums.Role;
import whz.pti.eva.praktikum_03.security.domain.User;
import whz.pti.eva.praktikum_03.service.CartService;
import whz.pti.eva.praktikum_03.service.CustomerService;
import whz.pti.eva.praktikum_03.service.ItemService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * The class Cart controller test.
 *
 * @author Isaev A. Nurmanbetov B.
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(CartController.class)
class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CartService mockCartService;
    @MockBean
    private ItemService mockItemService;
    @MockBean
    private CustomerService mockCustomerService;

    /**
     * Test to cart.
     *
     * @throws Exception the exception
     */
    @Test
    void testToCart() throws Exception {

        final User user = new User();
        user.setEmail("email");
        user.setLoginName("loginName");
        user.setPasswordHash("password");
        user.setRole(Role.USER);
        user.setActive(true);
        final CustomerDTO customerDTO = new CustomerDTO("id", "firstName", "lastName", "loginName", "passwordHash", "user", true, List.of(new DeliveryAddress("street", "houseNumber", "town", "postalCode", List.of(new Customer("firstName", "lastName", "loginName", "passwordHash", true, user, List.of())))));
        when(mockCustomerService.findByUserId("userId")).thenReturn(customerDTO);

        final CartDTO cartDTO = new CartDTO(0L, 0, Map.ofEntries(Map.entry("value", new Item(0, new Pizza("name", new BigDecimal("0.00"), new BigDecimal("0.00"), new BigDecimal("0.00")), PizzaSize.LARGE))), "userId");
        when(mockCartService.findCartByCustomer("customerId")).thenReturn(cartDTO);

        final MockHttpServletResponse response = mockMvc.perform(get("/cart/")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(view().name("cart"))
                .andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    /**
     * Test remove item from cart.
     *
     * @throws Exception the exception
     */
    @Test
    void testRemoveItemFromCart() throws Exception {

        final User user = new User();
        user.setEmail("email");
        user.setLoginName("loginName");
        user.setPasswordHash("password");
        user.setRole(Role.USER);
        user.setActive(true);
        final CustomerDTO customerDTO = new CustomerDTO("id", "firstName", "lastName", "loginName", "passwordHash", "user", true, List.of(new DeliveryAddress("street", "houseNumber", "town", "postalCode", List.of(new Customer("firstName", "lastName", "loginName", "passwordHash", true, user, List.of())))));
        when(mockCustomerService.findByUserId("userId")).thenReturn(customerDTO);

        final CartDTO cartDTO = new CartDTO(0L, 0, Map.ofEntries(Map.entry("value", new Item(0, new Pizza("name", new BigDecimal("0.00"), new BigDecimal("0.00"), new BigDecimal("0.00")), PizzaSize.LARGE))), "userId");
        when(mockCartService.findCartByCustomer("customerId")).thenReturn(cartDTO);

        final MockHttpServletResponse response = mockMvc.perform(get("/cart/remove")
                .param("itemKey", "itemKey")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/cart/"))
                .andReturn().getResponse();
    }

    /**
     * Test decrease quantity of item.
     *
     * @throws Exception the exception
     */
    @Test
    void testDecrease() throws Exception {

        final User user = new User();
        user.setEmail("email");
        user.setLoginName("loginName");
        user.setPasswordHash("password");
        user.setRole(Role.USER);
        user.setActive(true);
        final CustomerDTO customerDTO = new CustomerDTO("id", "firstName", "lastName", "loginName", "passwordHash", "user", true, List.of(new DeliveryAddress("street", "houseNumber", "town", "postalCode", List.of(new Customer("firstName", "lastName", "loginName", "passwordHash", true, user, List.of())))));
        when(mockCustomerService.findByUserId("userId")).thenReturn(customerDTO);

        final CartDTO cartDTO = new CartDTO(0L, 0, Map.ofEntries(Map.entry("value", new Item(0, new Pizza("name", new BigDecimal("0.00"), new BigDecimal("0.00"), new BigDecimal("0.00")), PizzaSize.LARGE))), "userId");
        when(mockCartService.findCartByCustomer("customerId")).thenReturn(cartDTO);

        final MockHttpServletResponse response = mockMvc.perform(get("/cart/decrease")
                .param("itemKey", "itemKey")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/cart/"))
                .andReturn().getResponse();
    }

    /**
     * Test increase quantity of item.
     *
     * @throws Exception the exception
     */
    @Test
    void testIncrease() throws Exception {

        final User user = new User();
        user.setEmail("email");
        user.setLoginName("loginName");
        user.setPasswordHash("password");
        user.setRole(Role.USER);
        user.setActive(true);
        final CustomerDTO customerDTO = new CustomerDTO("id", "firstName", "lastName", "loginName", "passwordHash", "user", true, List.of(new DeliveryAddress("street", "houseNumber", "town", "postalCode", List.of(new Customer("firstName", "lastName", "loginName", "passwordHash", true, user, List.of())))));
        when(mockCustomerService.findByUserId("userId")).thenReturn(customerDTO);

        final CartDTO cartDTO = new CartDTO(0L, 0, Map.ofEntries(Map.entry("value", new Item(0, new Pizza("name", new BigDecimal("0.00"), new BigDecimal("0.00"), new BigDecimal("0.00")), PizzaSize.LARGE))), "userId");
        when(mockCartService.findCartByCustomer("customerId")).thenReturn(cartDTO);

        final MockHttpServletResponse response = mockMvc.perform(get("/cart/increase")
                .param("itemKey", "itemKey")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/cart/"))
                .andReturn().getResponse();
    }
}
