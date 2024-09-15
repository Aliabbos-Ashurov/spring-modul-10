package com.pdp.springm10.service;

import com.pdp.springm10.entity.UrlMapping;
import com.pdp.springm10.repository.UrlMappingRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Service
public class UrlMappingService {

    private final UrlMappingRepository repository;

    @Autowired
    public UrlMappingService(UrlMappingRepository repository) {
        this.repository = repository;
    }

    public String shortenUrl(String longUrl) {
        String shortUrlCode = UUID.randomUUID().toString().substring(0, 8);
        UrlMapping mapping = new UrlMapping();
        mapping.setShortUrl(shortUrlCode);
        mapping.setLongUrl(longUrl);
        repository.save(mapping);
        return shortUrlCode;
    }

    public String getUrl(String shortUrl) {
        UrlMapping mapping = repository.findByShortUrl(shortUrl);
        return mapping != null ? mapping.getLongUrl() : "URL not found";
    }

    public void deleteUrl(String shortUrl) {
        UrlMapping mapping = repository.findByShortUrl(shortUrl);
        if (mapping != null) {
            repository.delete(mapping);
        }
    }
}


