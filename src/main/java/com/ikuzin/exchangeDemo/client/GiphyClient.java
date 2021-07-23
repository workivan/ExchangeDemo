package com.ikuzin.exchangeDemo.client;

import com.ikuzin.exchangeDemo.dto.gifDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "giphy", url = "${giphy.url}")
public interface GiphyClient {

    @GetMapping("/gifs/random")
    gifDto getGIFByTag(@RequestParam("tag") String tag, @RequestParam("api_key") String app_id);
}
