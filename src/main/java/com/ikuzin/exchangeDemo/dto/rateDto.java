package com.ikuzin.exchangeDemo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedHashMap;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class rateDto {
    @JsonProperty("rates")
    private Map<String,? extends Number> rates;

    public void setRates(LinkedHashMap<String, ? extends Number> rates) { this.rates = rates; }
    public Number getRateToValuta(String valuta){
        return rates.get(valuta);
    }
}
