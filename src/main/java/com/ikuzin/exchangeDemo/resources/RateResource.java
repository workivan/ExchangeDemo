package com.ikuzin.exchangeDemo.resources;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedHashMap;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RateResource {
    @JsonProperty("rates")
    private LinkedHashMap<String,? extends Number> rates;

    public void setRates(LinkedHashMap<String, ? extends Number> rates) { this.rates = rates; }

    public Number getRateToValuta(String valuta){
        return rates.get(valuta);
    }
}
