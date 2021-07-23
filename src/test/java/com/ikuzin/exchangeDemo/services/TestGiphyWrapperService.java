package com.ikuzin.exchangeDemo.services;

import com.ikuzin.exchangeDemo.clients.GiphyClient;
import com.ikuzin.exchangeDemo.exceptions.CallingExternalAPIException;
import com.ikuzin.exchangeDemo.resources.GIFResource;
import com.ikuzin.exchangeDemo.resources.GIFTag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.net.URI;
import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TestGiphyWrapperService {

    @MockBean private GiphyClient GClient;
    @Autowired private  GiphyWrapperService gwService;

    private GIFResource giphyResource;

    @BeforeEach
    public void init(){
        LinkedHashMap data = new LinkedHashMap();
        data.put("image_url", "https://yandex.ru");
        giphyResource = new GIFResource();
        giphyResource.setData(data);
    }

    @Test
    public void getGIFUriByTagTest_OK(){
        assertNotNull(GClient);
        assertNotNull(gwService);

        when(GClient.getGIFByTag(any(String.class), any(String.class))).thenReturn(this.giphyResource);
        var value = gwService.getGIFUriByTag(GIFTag.UP);
        assertEquals(value, URI.create("https://yandex.ru"));
    }

    @Test
    public void getGIFUriByTagTest_throwCallingExternalAPIException(){
        assertNotNull(GClient);
        assertNotNull(gwService);

        LinkedHashMap data = new LinkedHashMap();
        data.put("image_ur", "https://yandex.ru");
        giphyResource.setData(data);

        when(GClient.getGIFByTag(any(String.class), any(String.class))).thenReturn(this.giphyResource);
        Exception exception = assertThrows(
                CallingExternalAPIException.class,
                () -> gwService.getGIFUriByTag(GIFTag.UP)
        );

        String expectedMessage = "API did't return required url";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void getGIFUriByTagTest_throwIllegalArgumentException(){
        assertNotNull(GClient);
        assertNotNull(gwService);

        LinkedHashMap data = new LinkedHashMap();
        data.put("image_url", "http://adserver.adtech.de/adlink|3.0");
        giphyResource.setData(data);

        when(GClient.getGIFByTag(any(String.class), any(String.class))).thenReturn(this.giphyResource);
        assertThrows(
                IllegalArgumentException.class,
                () -> gwService.getGIFUriByTag(GIFTag.UP)
        );
    }
}
