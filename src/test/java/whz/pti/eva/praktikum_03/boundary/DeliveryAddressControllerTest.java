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
import org.springframework.security.test.context.support.WithSecurityContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import whz.pti.eva.praktikum_03.domain.Customer;
import whz.pti.eva.praktikum_03.domain.DeliveryAddress;
import whz.pti.eva.praktikum_03.dto.DeliveryAddressDTO;
import whz.pti.eva.praktikum_03.enums.Role;
import whz.pti.eva.praktikum_03.security.domain.CurrentUser;
import whz.pti.eva.praktikum_03.security.domain.User;
import whz.pti.eva.praktikum_03.service.DeliveryAddressService;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
@ContextConfiguration
@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
@WebMvcTest(DeliveryAddressController.class)
public class DeliveryAddressControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DeliveryAddressService mockDeliveryAddressService;


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
    @WithMockUser(username = "admin",password = "a1",roles = {"ADMIN"})
    public void testGetEditDeliveryAddressView() throws Exception {
        final User user = new User();
        user.setEmail("email");
        user.setLoginName("admin");
        user.setPasswordHash("a1");
        user.setRole(Role.ADMIN);
        user.setActive(true);
        final DeliveryAddressDTO deliveryAddressDTO = new DeliveryAddressDTO(0L, "street", "houseNumber", "town", "postalCode", List.of(new Customer("firstName", "lastName", "admin", "a1", true, user, List.of(new DeliveryAddress("street", "houseNumber", "town", "postalCode", List.of())))));
        when(mockDeliveryAddressService.findDeliveryAddressById(0L)).thenReturn(deliveryAddressDTO);

        final MockHttpServletResponse response = mockMvc.perform(get("/deliveryaddress/update/{id}", 0)
                .with(user("admin"))
                .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void testUpdateDeliveryAddress() throws Exception {
        // Setup

        // Configure DeliveryAddressService.updateAddress(...).
        final User user = new User();
        user.setEmail("email");
        user.setLoginName("loginName");
        user.setPasswordHash("password");
        user.setRole(Role.USER);
        user.setActive(false);
        final DeliveryAddress deliveryAddress = new DeliveryAddress("street", "houseNumber", "town", "postalCode", List.of(new Customer("firstName", "lastName", "loginName", "passwordHash", false, user, List.of())));
        when(mockDeliveryAddressService.updateAddress(any(DeliveryAddressDTO.class))).thenReturn(deliveryAddress);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/deliveryaddress/update/{id}", 0)
                .param("deliveryaddress", "deliveryaddress")
                .with(user("username").roles("USER"))
                .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("expectedResponse", response.getContentAsString());
    }
}
