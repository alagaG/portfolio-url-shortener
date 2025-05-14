package dev.alagag.portfolio.url.shortener.controller;

import dev.alagag.portfolio.url.shortener.dto.ShortUrlDestructionResponse;
import dev.alagag.portfolio.url.shortener.dto.ShortUrlListResponse;
import dev.alagag.portfolio.url.shortener.dto.ShortenerWhitelistResponse;
import dev.alagag.portfolio.url.shortener.entity.ShortenedUrl;
import dev.alagag.portfolio.url.shortener.service.UrlShorteningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/links")
public class LinkManagmentController {

    @Autowired
    private UrlShorteningService shorteningService;

    @GetMapping
    public ResponseEntity<ShortUrlListResponse> listShortenedURLs() {
        List<ShortenedUrl> urlList = shorteningService.listAll();
        return ResponseEntity.ok(new ShortUrlListResponse(urlList));
    }

    @DeleteMapping("/{targetUrlKey}")
    public ResponseEntity<ShortUrlDestructionResponse> destroyShortenedURL(@PathVariable(name="targetUrlKey") String urlKey) {
        boolean result = shorteningService.delete(urlKey);
        return ResponseEntity.ok(result
                ? ShortUrlDestructionResponse.forSuccess()
                : ShortUrlDestructionResponse.forFailure());
    }

    @GetMapping("/whitelist")
    public ResponseEntity<ShortenerWhitelistResponse> showShortenerWhitelist() {
        ShortenerWhitelistResponse response = new ShortenerWhitelistResponse(shorteningService.getWhitelist());
        return ResponseEntity.ok(response);
    }

}
