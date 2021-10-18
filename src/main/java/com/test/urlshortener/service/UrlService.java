package com.test.urlshortener.service;

import com.test.urlshortener.core.UrlShortenerCore;
import com.test.urlshortener.dao.UrlRepository;
import com.test.urlshortener.model.CustomURL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UrlService {

    @Autowired
    private UrlRepository urlRepository;

    public CustomURL getUrlByShortId(String shortId) throws Exception {
        Optional<CustomURL> url = urlRepository.findByShortId(shortId);
        url.orElseThrow(() -> new Exception("No short id was found by this id"));
        return url.get();
    }

    public Optional<CustomURL> getCustomUrlByOriginUrl(String originUrl){
        return urlRepository.findByOriginUrl(originUrl);
    }

    public CustomURL createCustomUrl(String originUrl){

        String shortId = UrlShortenerCore.encodeURL(originUrl);

        CustomURL newUrl = new CustomURL();
        newUrl.setOriginUrl(originUrl);
        newUrl.setShortId(shortId);
        newUrl.setDate(new Date());

        return urlRepository.save(newUrl);
    }

}
