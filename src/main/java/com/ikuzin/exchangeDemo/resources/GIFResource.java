package com.ikuzin.exchangeDemo.resources;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedHashMap;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GIFResource {
    @JsonProperty("data")
    private LinkedHashMap data;

    public void setData(LinkedHashMap data) { this.data = data; }

    public String getGIFUrl() {
        return  (String) data.get("image_url");
    }
}

