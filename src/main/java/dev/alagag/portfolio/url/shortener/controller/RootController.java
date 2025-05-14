package dev.alagag.portfolio.url.shortener.controller;

import dev.alagag.portfolio.url.shortener.exception.UserErrorCodes;
import dev.alagag.portfolio.url.shortener.exception.UserErrorMessageException;
import dev.alagag.portfolio.url.shortener.service.UrlShorteningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class RootController {

    @Autowired
    private UrlShorteningService shorteningService;

    @GetMapping("/{urlKey}")
    public ResponseEntity<Object> shorteningUrlRedirect(@PathVariable(name = "urlKey") String urlKey) {
        String redirectDestiny = shorteningService.redirectFor(urlKey);
        if (redirectDestiny.isEmpty()) throw new UserErrorMessageException(UserErrorCodes.ORIGINAL_URL_NOT_FOUND);
        return ResponseEntity.status(HttpStatus.FOUND).header("Location", redirectDestiny).build();
    }

}
