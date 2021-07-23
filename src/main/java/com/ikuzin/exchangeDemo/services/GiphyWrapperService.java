package com.ikuzin.exchangeDemo.services;

import com.ikuzin.exchangeDemo.clients.GiphyClient;
import com.ikuzin.exchangeDemo.exceptions.CallingExternalAPIException;
import com.ikuzin.exchangeDemo.resources.GIFResource;
import com.ikuzin.exchangeDemo.resources.GIFTag;
import com.ikuzin.exchangeDemo.utils.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.net.URI;

@Component
public class GiphyWrapperService extends CommonService {
    private final GiphyClient GClient;

    GiphyWrapperService(@Autowired GiphyClient GClient, @Autowired Environment env) {
        super(env);
        this.GClient = GClient;
    }

    public URI getGIFUriByTag(GIFTag tag) {
        GIFResource giphyInfo = GClient.getGIFByTag(tag.getTag(), env.getRequiredProperty("giphy.app_id"));
        String gifUrl = giphyInfo.getGIFUrl();
        if (gifUrl == null) {
            throw new CallingExternalAPIException("API did't return required url");
        }
        return URI.create(gifUrl);
    }
}
