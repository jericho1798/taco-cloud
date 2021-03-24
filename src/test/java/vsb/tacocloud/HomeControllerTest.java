package vsb.tacocloud;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest()           //Web test for HomeController
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;                //Inject MockMvc

    @Test
    public void testHomePage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())                                 //Expects HTTP 200
                .andExpect(view().name("home"))             //Expects home view
                .andExpect(content().string(                               //Expects Welcome to...
                        containsString("Welcome to...")));
    }


}
