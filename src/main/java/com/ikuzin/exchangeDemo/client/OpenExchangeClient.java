package com.ikuzin.exchangeDemo.client;


import com.ikuzin.exchangeDemo.dto.rateDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@FeignClient(name = "openexchange", url="${openexchange.url}")
public interface OpenExchangeClient {

    @GetMapping(value = "/historical/{date}.json")
    rateDto getRateByDate(
            @PathVariable("date")  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam("app_id") String app_id,
            @RequestParam("base") String base
    );
}
