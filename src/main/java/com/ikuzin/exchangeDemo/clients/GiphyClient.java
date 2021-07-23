package com.ikuzin.exchangeDemo.clients;

import com.ikuzin.exchangeDemo.resources.GIFResource;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "giphy", url = "${giphy.url}")
public interface GiphyClient {

    @GetMapping("/gifs/random")
    GIFResource getGIFByTag(@RequestParam("tag") String tag, @RequestParam("api_key") String app_id);
}
