package com.pdp.springm10.command;

import com.pdp.springm10.service.UrlMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

/**
 * @author Aliabbos Ashurov
 * @since 15/September/2024  16:12
 **/
@ShellComponent
public class UrlCommands {
    @Autowired
    private UrlMappingService urlMappingService;

    @ShellMethod(value = "Shorten a URL", key = "shorten-url")
    public String shortenUrl(String longUrl) {
        return urlMappingService.shortenUrl(longUrl);
    }

    @ShellMethod(value = "Get the long URL from a shortened URL", key = "get-url")
    public String getUrl(String shortUrl) {
        return urlMappingService.getUrl(shortUrl);
    }

    @ShellMethod(value = "Delete a URL mapping", key = "delete-url")
    public void deleteUrl(String shortUrl) {
        urlMappingService.deleteUrl(shortUrl);
    }
}

