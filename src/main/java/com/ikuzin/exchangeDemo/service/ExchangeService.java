package com.ikuzin.exchangeDemo.service;
import com.ikuzin.exchangeDemo.client.AnyUriClient;
import com.ikuzin.exchangeDemo.utils.gifTag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ExchangeService{

    private final GiphyWrapperService gwService;
    private final OpenExchangeWrapperService oewService;
    private final AnyUriClient auClient;

    public byte[] getGIFByValutaTrendTo(String valuta) {
        LocalDate today = LocalDate.now();
        double currentRate = oewService.getRateByDate(today, valuta).doubleValue();
        double yesterdayRate = oewService.getRateByDate(today.minusDays(1), valuta).doubleValue();

        URI gifURI = currentRate < yesterdayRate ? gwService.getGIFUriByTag(gifTag.DOWN) : gwService.getGIFUriByTag(gifTag.UP);
        return this.auClient.getByUrl(gifURI);
    }
}
