package whz.pti.eva.praktikum_03.boundary;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
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
import whz.pti.eva.praktikum_03.service.PizzaService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * The class Main controller test.
 *
 * @author Isaev A. Nurmanbetov B.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(MainController.class)
public class MainControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PizzaService mockPizzaService;
    @MockBean
    private CartService mockCartService;
    @MockBean
    private ItemService mockItemService;
    @MockBean
    private CustomerService mockCustomerService;

    /**
     * Test get login page.
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetLoginPage() throws Exception {

        final MockHttpServletResponse response = mockMvc.perform(get("/login")
                .param("error", "error")
                .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    /**
     * Test access denied.
     *
     * @throws Exception the exception
     */
    @Test
    public void testAccessDenied() throws Exception {
        final MockHttpServletResponse response = mockMvc.perform(get("/403")
                .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    /**
     * Test root.
     *
     * @throws Exception the exception
     */
    @Test
    public void testRoot() throws Exception {

        final List<Pizza> pizzas = List.of(new Pizza("name", new BigDecimal("0.00"), new BigDecimal("0.00"), new BigDecimal("0.00")));
        when(mockPizzaService.listAllPizza()).thenReturn(pizzas);

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

        when(mockCartService.calculateTotalAmountOfPizzasInItemsInCart(any(CartDTO.class))).thenReturn(0);
        when(mockCartService.calculateTotalPriceOfPizzaInItemsInCart(any(CartDTO.class))).thenReturn(new BigDecimal("0.00"));

        final MockHttpServletResponse response = mockMvc.perform(get("/index")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }
}
