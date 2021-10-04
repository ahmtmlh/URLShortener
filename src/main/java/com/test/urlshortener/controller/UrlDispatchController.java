package com.test.urlshortener.controller;

import com.test.urlshortener.model.CustomURL;
import com.test.urlshortener.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/dispatch")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class UrlDispatchController {

    @Autowired
    private UrlService urlService;


    @RequestMapping(method = {RequestMethod.GET, RequestMethod.HEAD, RequestMethod.POST}, value= "{id}")
    public ResponseEntity<?> dispatch(@PathVariable(name = "id") @Valid  String id, HttpServletRequest request){
        try{
            CustomURL customURL = urlService.getUrlByShortId(id);
            URI newUri = new URI(customURL.getOriginUrl());
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setLocation(newUri);
            return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
        } catch (Exception ignore) {
            return ResponseEntity.notFound().build();
        }
    }
}
