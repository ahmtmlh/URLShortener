package com.test.urlshortener.controller;

import com.test.urlshortener.model.CustomURL;
import com.test.urlshortener.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/dispatch")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class UrlDispatchController {

    @Autowired
    private UrlService urlService;


    @RequestMapping(method = {RequestMethod.GET, RequestMethod.HEAD, RequestMethod.POST}, value= "{id}")
    public RedirectView dispatch(@PathVariable(name = "id") @Valid  String id, HttpServletRequest request){

        RedirectView redirectView = new RedirectView();

        try{
            CustomURL customURL = urlService.getUrlByShortId(id);
            redirectView.setUrl(customURL.getOriginUrl());
        } catch (Exception e) {
            redirectView.setUrl(request.getRequestURI());
        }

        return redirectView;
    }
}
