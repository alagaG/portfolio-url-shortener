package dev.alagag.portfolio.url.shortener.controller;

import dev.alagag.portfolio.url.shortener.config.ServerProperties;
import dev.alagag.portfolio.url.shortener.dto.ShortUrlCreationRequest;
import dev.alagag.portfolio.url.shortener.dto.ShortUrlCreationResponse;
import dev.alagag.portfolio.url.shortener.exception.UserErrorCodes;
import dev.alagag.portfolio.url.shortener.exception.UserErrorMessageException;
import dev.alagag.portfolio.url.shortener.service.UrlShorteningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/shorten")
public class UrlShortenerController {

    @Autowired
    private ServerProperties serverProperties;
    @Autowired
    private UrlShorteningService shorteningService;

    @PostMapping
    public ResponseEntity<ShortUrlCreationResponse> shorten(@RequestBody ShortUrlCreationRequest body) {
        String originalUrl = body.originalUrl();
        if (originalUrl == null) throw new UserErrorMessageException(UserErrorCodes.INVALID_BODY);

        String shortenedUrl = "http://" + serverProperties.getHostname() + "/" + shorteningService.shorten(originalUrl);
        return ResponseEntity.ok(new ShortUrlCreationResponse(shortenedUrl, originalUrl));
    }

}
