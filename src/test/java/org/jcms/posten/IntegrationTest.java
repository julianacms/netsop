package org.jcms.posten;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getPrice() throws Exception {
        this.mockMvc.perform(
                    get("/api/price")
                        .param("weight", "12")
                            .param("quantity", "4"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("430")));
    }

    @Test
    public void getPrice_invalid_weight() throws Exception {
        this.mockMvc.perform(
                        get("/api/price")
                                .param("weight", "54")
                                .param("quantity", "4"))
                .andExpect(status().is4xxClientError());
    }
}

