package com.ikuzin.exchangeDemo.service;

import com.ikuzin.exchangeDemo.client.OpenExchangeClient;
import com.ikuzin.exchangeDemo.dto.rateDto;
import com.ikuzin.exchangeDemo.exception.CallingExternalAPIException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class OpenExchangeWrapperService{
    private final OpenExchangeClient OEClient;

    @Value("${openexchange.app_id}") private String appId;
    @Value("${baseValuta}") private String baseValuta;

    public Number getRateByDate(LocalDate date, String valuta) {
        rateDto rateInfo = OEClient.getRateByDate(date, appId, baseValuta);
        Number value = rateInfo.getRateToValuta(valuta);
        if (value == null) {
            throw new CallingExternalAPIException("required valuta:[" + valuta + "] was not find");
        }
        return value;
    }
}
