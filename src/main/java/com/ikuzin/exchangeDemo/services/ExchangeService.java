package com.ikuzin.exchangeDemo.services;
import com.ikuzin.exchangeDemo.clients.AnyUriClient;
import com.ikuzin.exchangeDemo.resources.GIFTag;
import com.ikuzin.exchangeDemo.utils.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.LocalDate;

@Service
public class ExchangeService extends CommonService {

    private final GiphyWrapperService gwService;
    private final OpenExchangeWrapperService oewService;
    private final AnyUriClient auClient;

    public ExchangeService(
            @Autowired AnyUriClient auClient,
            @Autowired GiphyWrapperService gwService,
            @Autowired OpenExchangeWrapperService oewService,
            @Autowired Environment env
    ) {
        super(env);
        this.auClient = auClient;
        this.oewService = oewService;
        this.gwService = gwService;
    }

    public byte[] getGIFByValutaTrendTo(String valuta) {
        LocalDate today = LocalDate.now();
        double currentRate = oewService.getRateByDate(today, valuta).doubleValue();
        double yesterdayRate = oewService.getRateByDate(today.minusDays(1), valuta).doubleValue();

        URI gifURI = currentRate < yesterdayRate ? gwService.getGIFUriByTag(GIFTag.DOWN) : gwService.getGIFUriByTag(GIFTag.UP);
        return this.auClient.getByUrl(gifURI);
    }
}
