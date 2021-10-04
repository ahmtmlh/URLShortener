package com.test.urlshortener.controller;

import com.test.urlshortener.model.CustomURL;
import com.test.urlshortener.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@RequestMapping("/create")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class UrlCreatorController {

    private static class URLResponse{
        private String originalURL;
        private String shortUrl;

        public String getOriginalURL() {
            return originalURL;
        }

        public void setOriginalURL(String originalURL) {
            this.originalURL = originalURL;
        }

        public String getShortUrl() {
            return shortUrl;
        }

        public void setShortUrl(String shortUrl) {
            this.shortUrl = shortUrl;
        }
    }

    @Autowired
    private UrlService urlService;

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.HEAD, RequestMethod.POST})
    public ResponseEntity<URLResponse> create(@RequestParam(name = "url") String url, HttpServletRequest request){
        /*
            Steps:
                1. Check if url exists
                2. If not, create url
                3  Return the url
         */

        URLResponse urlResponse = new URLResponse();
        String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();

        Optional<CustomURL> customURL = urlService.getCustomUrlByOriginUrl(url);

        if (customURL.isPresent()){
            urlResponse.originalURL = customURL.get().getOriginUrl();
            urlResponse.shortUrl = baseUrl + "/dispatch/" + customURL.get().getShortId();
        } else {
            CustomURL newUrl = urlService.createCustomUrl(url);
            urlResponse.originalURL = newUrl.getOriginUrl();
            urlResponse.shortUrl = baseUrl + "/dispatch/" + newUrl.getShortId();
        }

        return ResponseEntity.ok(urlResponse);
    }


}
