package com.ikuzin.exchangeDemo.services;

import com.ikuzin.exchangeDemo.clients.OpenExchangeClient;
import com.ikuzin.exchangeDemo.exceptions.CallingExternalAPIException;
import com.ikuzin.exchangeDemo.resources.RateResource;
import com.ikuzin.exchangeDemo.utils.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class OpenExchangeWrapperService extends CommonService {
    private final OpenExchangeClient OEClient;

    OpenExchangeWrapperService(@Autowired OpenExchangeClient client, @Autowired Environment env) {
        super(env);
        OEClient = client;
    }

    public Number getRateByDate(LocalDate date, String valuta) {
        RateResource rateInfo = OEClient.getRateByDate(
                date,
                env.getRequiredProperty("openexchange.app_id"),
                env.getRequiredProperty("baseValuta")
        );
        Number value = rateInfo.getRateToValuta(valuta);
        if (value == null) {
            throw new CallingExternalAPIException("required valuta:[" + valuta + "] was not find");
        }
        return value;
    }
}
