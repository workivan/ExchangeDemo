package com.ikuzin.exchangeDemo.client;

import com.ikuzin.exchangeDemo.utils.Utils;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.net.URI;

@FeignClient(name = "AnyURI", url = Utils.EMPTY_URL)
public interface AnyUriClient {

    @GetMapping
    byte[] getByUrl(URI baseUri);
}
