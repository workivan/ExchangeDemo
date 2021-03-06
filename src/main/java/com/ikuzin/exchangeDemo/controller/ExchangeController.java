package com.ikuzin.exchangeDemo.controller;

import com.ikuzin.exchangeDemo.service.ExchangeService;
import com.ikuzin.exchangeDemo.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class ExchangeController {
    private final ExchangeService exchangeService;

    @GetMapping(value = "/valuta/{valutaCode}", produces = MediaType.IMAGE_GIF_VALUE)
    public byte[] getValutaTrend(@PathVariable String valutaCode) {
        Utils.checkValutaPathVariable(valutaCode);
        return exchangeService.getGIFByValutaTrendTo(valutaCode.toUpperCase());
    }
}
