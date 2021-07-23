package com.ikuzin.exchangeDemo.service;

import com.ikuzin.exchangeDemo.client.OpenExchangeClient;
import com.ikuzin.exchangeDemo.exception.CallingExternalAPIException;
import com.ikuzin.exchangeDemo.dto.rateDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;


@SpringBootTest
public class TestOpenExchangeWrapperService {

    @MockBean private OpenExchangeClient OEClient;
    @Autowired private OpenExchangeWrapperService oeService;

    private String valuta;
    private rateDto rateDto;

    @BeforeEach
    public void init() {
        this.valuta = "RUB";

        LinkedHashMap<String, Double> rates = new LinkedHashMap<>();
        rates.put(this.valuta, 100.0);

        rateDto = new rateDto();
        rateDto.setRates(rates);
    }


    @Test
    public void getRateByDateTest_OK() {
        assertNotNull(OEClient);
        when(OEClient.getRateByDate(eq(LocalDate.now()), any(String.class), any(String.class))).thenReturn(this.rateDto);
        var value = (double)oeService.getRateByDate(LocalDate.now(), this.valuta);

        assertEquals(value, 100.0);
    }

    @Test
    public void getRateByDateTest_throwCallingExternalAPIException() {
        assertNotNull(OEClient);

        LinkedHashMap<String, Double> rates = new LinkedHashMap<>();
        rates.put("BUR", 100.0);
        this.rateDto.setRates(rates);

        when(OEClient.getRateByDate(eq(LocalDate.now()), any(String.class), any(String.class))).thenReturn(this.rateDto);
        Exception exception = assertThrows(
                CallingExternalAPIException.class,
                () -> oeService.getRateByDate(LocalDate.now(), this.valuta)
        );

        String expectedMessage = "required valuta:[" + this.valuta + "] was not find";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
