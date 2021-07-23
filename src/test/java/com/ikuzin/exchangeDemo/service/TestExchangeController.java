package com.ikuzin.exchangeDemo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TestExchangeController {

    @MockBean private ExchangeService exchangeService;
    @Autowired private MockMvc mockMvc;

    private byte[] gif;

    @BeforeEach
    public void init(){
        gif = new byte[]{};
    }

    @Test
    public void getValutaTrendTest_OK() throws Exception {
        assertNotNull(exchangeService);
        assertNotNull(mockMvc);

        byte[] gif = new byte[]{};
        when(exchangeService.getGIFByValutaTrendTo(any(String.class))).thenReturn(gif);
        this.mockMvc.perform(get("/valuta/RUB")).andExpect(status().isOk());
    }

    @Test
    public void getValutaTrendTest_return400ByValutaLess3() throws Exception {
        assertNotNull(exchangeService);
        assertNotNull(mockMvc);

        when(exchangeService.getGIFByValutaTrendTo(any(String.class))).thenReturn(gif);
        this.mockMvc.perform(get("/valuta/RU")).andExpect(status().isBadRequest());
    }

    @Test
    public void getValutaTrendTest_return400ByValutaContainsDigit() throws Exception {
        assertNotNull(exchangeService);
        assertNotNull(mockMvc);

        when(exchangeService.getGIFByValutaTrendTo(any(String.class))).thenReturn(gif);
        this.mockMvc.perform(get("/valuta/R4t")).andExpect(status().isBadRequest());
    }
}
