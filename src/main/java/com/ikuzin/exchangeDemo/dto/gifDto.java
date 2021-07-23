package com.ikuzin.exchangeDemo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class gifDto {
    @JsonProperty("data")
    private Map<String,? super String> data;

    public void setData(Map<String,? super String> data) { this.data = data; }

    public String getGIFUrl() {
        return  (String) data.get("image_url");
    }
}

