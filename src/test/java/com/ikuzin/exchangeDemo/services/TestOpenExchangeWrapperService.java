package com.ikuzin.exchangeDemo.services;

import com.ikuzin.exchangeDemo.clients.OpenExchangeClient;
import com.ikuzin.exchangeDemo.exceptions.CallingExternalAPIException;
import com.ikuzin.exchangeDemo.resources.RateResource;
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
    private RateResource rateResource;

    @BeforeEach
    public void init() {
        this.valuta = "RUB";

        LinkedHashMap<String, Double> rates = new LinkedHashMap<>();
        rates.put(this.valuta, 100.0);

        rateResource = new RateResource();
        rateResource.setRates(rates);
    }


    @Test
    public void getRateByDateTest_OK() {
        assertNotNull(OEClient);
        when(OEClient.getRateByDate(eq(LocalDate.now()), any(String.class), any(String.class))).thenReturn(this.rateResource);
        var value = (double)oeService.getRateByDate(LocalDate.now(), this.valuta);

        assertEquals(value, 100.0);
    }

    @Test
    public void getRateByDateTest_throwCallingExternalAPIException() {
        assertNotNull(OEClient);

        LinkedHashMap<String, Double> rates = new LinkedHashMap<>();
        rates.put("BUR", 100.0);
        this.rateResource.setRates(rates);

        when(OEClient.getRateByDate(eq(LocalDate.now()), any(String.class), any(String.class))).thenReturn(this.rateResource);
        Exception exception = assertThrows(
                CallingExternalAPIException.class,
                () -> oeService.getRateByDate(LocalDate.now(), this.valuta)
        );

        String expectedMessage = "required valuta:[" + this.valuta + "] was not find";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
