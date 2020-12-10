package whz.pti.eva.praktikum_03.boundary;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import whz.pti.eva.praktikum_03.domain.*;
import whz.pti.eva.praktikum_03.dto.CartDTO;
import whz.pti.eva.praktikum_03.dto.CustomerDTO;
import whz.pti.eva.praktikum_03.enums.PizzaSize;
import whz.pti.eva.praktikum_03.enums.Role;
import whz.pti.eva.praktikum_03.security.boundary.CurrentUserControllerAdvice;
import whz.pti.eva.praktikum_03.security.domain.CurrentUser;
import whz.pti.eva.praktikum_03.security.domain.User;
import whz.pti.eva.praktikum_03.service.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@ContextConfiguration
@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
@WebMvcTest(OrderedController.class)
public class OrderedControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private PizzaService mockPizzaService;
    @MockBean
    private CartService mockCartService;

    @MockBean
    private CurrentUserControllerAdvice currentUserControllerAdvice;

    @MockBean
    CurrentUser currentUser;
    @Autowired
    private WebApplicationContext wac;


    @MockBean
    private ItemService mockItemService;
    @MockBean
    private CustomerService mockCustomerService;
    @MockBean
    private OrderedService mockOrderedService;
    @MockBean
    private OrderedItemService mockOrderedItemService;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .build();
    }

    @Test
    @WithMockUser(username = "loginName", roles = {"USER"}, authorities = {"USER"})
    public void testRoot() throws Exception {
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
        final User user1 = new User();
        user1.setEmail("email");
        user1.setLoginName("loginName");
        user1.setPasswordHash("password");
        user1.setRole(Role.USER);
        user1.setActive(true);
        final Ordered ordered = new Ordered(0, new Customer("firstName", "lastName", "loginName", "passwordHash", true, user1, List.of(new DeliveryAddress("street", "houseNumber", "town", "postalCode", List.of()))), List.of(new OrderedItem(0L, "name", 0, "userId", PizzaSize.LARGE)));
        when(mockOrderedItemService.addOrderedItem(any(CartDTO.class), any(CustomerDTO.class))).thenReturn(ordered);

        when(mockItemService.deleteItems(any(CartDTO.class), any(CustomerDTO.class))).thenReturn(false);
        final User user2 = new User();
        user2.setEmail("email");
        user2.setLoginName("loginName");
        user2.setPasswordHash("password");
        user2.setRole(Role.USER);
        user2.setActive(true);

        final List<Ordered> orderedList = List.of(new Ordered(0, new Customer("firstName", "lastName", "loginName", "passwordHash", true, user2, List.of(new DeliveryAddress("street", "houseNumber", "town", "postalCode", List.of()))), List.of(new OrderedItem(0L, "name", 0, "userId", PizzaSize.LARGE))));
        when(mockOrderedService.findAllByCustomerId("customerId")).thenReturn(orderedList);


        final MockHttpServletResponse response = mockMvc.perform(get("/ordered/")
                .with(user("loginName").authorities(currentUser.getAuthorities()))
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(view().name("ordered"))
                .andReturn().getResponse();

    }
}
