package whz.pti.eva.praktikum_03.boundary;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import whz.pti.eva.praktikum_03.domain.Customer;
import whz.pti.eva.praktikum_03.domain.DeliveryAddress;
import whz.pti.eva.praktikum_03.domain.Item;
import whz.pti.eva.praktikum_03.domain.Pizza;
import whz.pti.eva.praktikum_03.dto.CartDTO;
import whz.pti.eva.praktikum_03.dto.CustomerDTO;
import whz.pti.eva.praktikum_03.enums.PizzaSize;
import whz.pti.eva.praktikum_03.enums.Role;
import whz.pti.eva.praktikum_03.security.boundary.CurrentUserControllerAdvice;
import whz.pti.eva.praktikum_03.security.domain.CurrentUser;
import whz.pti.eva.praktikum_03.security.domain.User;
import whz.pti.eva.praktikum_03.service.CartService;
import whz.pti.eva.praktikum_03.service.CustomerService;
import whz.pti.eva.praktikum_03.service.ItemService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration
@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
@WebMvcTest(ItemController.class)
public class ItemControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private ItemService mockItemService;
    @MockBean
    private CartService mockCartService;
    @MockBean
    private CustomerService mockCustomerService;

    @MockBean
    private CurrentUserControllerAdvice currentUserControllerAdvice;

    @MockBean
    CurrentUser currentUser;
    @MockBean
    User user;
    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .build();
    }


    @Test
    @WithMockUser(username = "loginName", roles = {"USER"}, authorities = {"USER"})
    public void testAddItemToCart() throws Exception {
        final User user = new User();
        user.setEmail("email");
        user.setLoginName("loginName");
        user.setPasswordHash("password");
        user.setRole(Role.USER);
        user.setActive(false);
        final CustomerDTO customerDTO = new CustomerDTO("id", "firstName", "lastName", "loginName", "passwordHash", "user", false, List.of(new DeliveryAddress("street", "houseNumber", "town", "postalCode", List.of(new Customer("firstName", "lastName", "loginName", "passwordHash", false, user, List.of())))));
        when(mockCustomerService.findByUserId("userId")).thenReturn(customerDTO);
        final CartDTO cartDTO = new CartDTO(0L, 0, Map.ofEntries(Map.entry("value", new Item(0, new Pizza("name", new BigDecimal("0.00"), new BigDecimal("0.00"), new BigDecimal("0.00")), PizzaSize.LARGE))), "userId");
        when(mockCartService.findCartByCustomer("customerId")).thenReturn(cartDTO);
        final MockHttpServletResponse response = mockMvc.perform(post("/item/add")
                .with(user("loginName").authorities(currentUser.getAuthorities()))
                .param("pizzaSize", "pizzaSize")
                .param("menge", "0")
                .param("pizzaName", "pizzaName")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/index"))
                .andReturn().getResponse();

        verify(mockItemService).addItem(eq(PizzaSize.LARGE), eq(0), eq("pizza"), any(CartDTO.class));
        verify(mockItemService).addItem(eq(PizzaSize.LARGE), eq(0), eq("pizza"), any(CartDTO.class), any(CustomerDTO.class));
    }
}
