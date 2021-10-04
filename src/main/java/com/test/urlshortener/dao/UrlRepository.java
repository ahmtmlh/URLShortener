package com.test.urlshortener.dao;

import com.test.urlshortener.model.CustomURL;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UrlRepository extends CrudRepository<CustomURL, Long> {

    CustomURL findTop1ByOrderByDateDesc();
    Optional<CustomURL> findByShortId(String shortId);
    Optional<CustomURL> findByOriginUrl(String originUrl);

}
