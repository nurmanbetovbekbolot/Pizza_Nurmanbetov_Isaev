package whz.pti.eva.praktikum_03.boundary;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import whz.pti.eva.praktikum_03.domain.Pizza;
import whz.pti.eva.praktikum_03.service.PizzaService;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 * The class Pizza controller test.
 *
 * @author Isaev A. Nurmanbetov B.
 */
@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@WebMvcTest(PizzaController.class)
public class PizzaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PizzaService mockPizzaService;

    /**
     * Test list all pizza.
     *
     * @throws Exception the exception
     */
    @Test
    public void testListAllPizza() throws Exception {
        final List<Pizza> pizzas = List.of(new Pizza("name", new BigDecimal("0.00"), new BigDecimal("0.00"), new BigDecimal("0.00")));
        when(mockPizzaService.listAllPizza()).thenReturn(pizzas);

        final MockHttpServletResponse response = mockMvc.perform(get("/pizza/all")
                .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }
}
