package com.ikuzin.exchangeDemo.service;

import com.ikuzin.exchangeDemo.client.GiphyClient;
import com.ikuzin.exchangeDemo.exception.CallingExternalAPIException;
import com.ikuzin.exchangeDemo.dto.gifDto;
import com.ikuzin.exchangeDemo.utils.gifTag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class GiphyWrapperService {
    private final GiphyClient GClient;
    @Value("${giphy.app_id}")
    private String appId;

    public URI getGIFUriByTag(gifTag tag) {
        gifDto giphyInfo = GClient.getGIFByTag(tag.getTag(), appId);
        String gifUrl = giphyInfo.getGIFUrl();
        if (gifUrl == null) {
            throw new CallingExternalAPIException("API did't return required url");
        }
        return URI.create(gifUrl);
    }
}
