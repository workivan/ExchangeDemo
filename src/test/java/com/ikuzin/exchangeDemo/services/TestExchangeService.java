package com.ikuzin.exchangeDemo.services;

import com.ikuzin.exchangeDemo.clients.AnyUriClient;
import com.ikuzin.exchangeDemo.resources.GIFTag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.net.URI;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TestExchangeService {

    @MockBean private  GiphyWrapperService gwService;
    @MockBean private  OpenExchangeWrapperService oewService;
    @MockBean private  AnyUriClient auClient;

    @Autowired private ExchangeService exService;

//    @BeforeEach
//    public void init(){
//        when(oewService.getRateByDate(eq(LocalDate.now()), any(String.class))).thenReturn(100);
//        when(oewService.getRateByDate(eq(LocalDate.now().minusDays(1)), any(String.class))).thenReturn(101);
//
//        URI urlLess = URI.create("https://yandex.ru");
//        when(gwService.getGIFUriByTag(eq(GIFTag.DOWN))).thenReturn(urlLess);
//
//        URI urlMore = URI.create("https://google.com");
//        when(gwService.getGIFUriByTag(eq(GIFTag.UP))).thenReturn(urlMore);
//
//        when(auClient.getByUrl(eq(urlLess))).thenReturn(new byte[]{});
//        when(auClient.getByUrl(eq(urlMore))).thenReturn(new byte[]{(byte)0xe0});
//    }


    @Test
    public void getGIFByValutaTrendTo_LessOK(){
        when(oewService.getRateByDate(eq(LocalDate.now()), any(String.class))).thenReturn(100);
        when(oewService.getRateByDate(eq(LocalDate.now().minusDays(1)), any(String.class))).thenReturn(101);
        URI urlLess = URI.create("https://yandex.ru");
        when(gwService.getGIFUriByTag(eq(GIFTag.DOWN))).thenReturn(urlLess);
        when(auClient.getByUrl(eq(urlLess))).thenReturn(new byte[]{});

        byte[] gif = exService.getGIFByValutaTrendTo("RUB");
        assertEquals(0, gif.length);
    }

    @Test
    public void getGIFByValutaTrendTo_MoreOK(){
        when(oewService.getRateByDate(eq(LocalDate.now()), any(String.class))).thenReturn(101);
        when(oewService.getRateByDate(eq(LocalDate.now().minusDays(1)), any(String.class))).thenReturn(100);
        URI urlMore = URI.create("https://google.com");
        when(gwService.getGIFUriByTag(eq(GIFTag.UP))).thenReturn(urlMore);
        when(auClient.getByUrl(eq(urlMore))).thenReturn(new byte[]{(byte) 0xe0});

        byte[] gif = exService.getGIFByValutaTrendTo("RUB");
        assertTrue(gif.length > 0);
    }
}
