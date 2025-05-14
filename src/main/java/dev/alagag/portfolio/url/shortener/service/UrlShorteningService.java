package dev.alagag.portfolio.url.shortener.service;

import dev.alagag.portfolio.url.shortener.entity.ShortenedUrl;
import dev.alagag.portfolio.url.shortener.repository.ShortenedUrlRepository;
import dev.alagag.portfolio.url.shortener.utils.UniversalEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

@Service
public class UrlShorteningService {

    @Autowired
    private ShortenedUrlRepository repository;
    private final HashMap<String, Boolean> whitelist;
    private final UniversalEncoder encoder;
    private final Random rng;

    public UrlShorteningService() {
        HashMap<String, Boolean> whitelist = new HashMap<>();
        whitelist.put("https://www.google.com/", true);
        whitelist.put("https://www.youtube.com/", true);
        this.whitelist = whitelist;

        //  Base62 encoder
        this.encoder = new UniversalEncoder(
                "0123456789"
                + "abcdefghijklmnopqrstuvwxyz"
                + "ABCDEFGHIJKLMNOPQRSTUVWXYZ");

        this.rng = new Random();
    }

    public String shorten(String originalUrl) {
        // Erase expired URLs
        OffsetDateTime currentDateTime = OffsetDateTime.now();
        OffsetDateTime dayBeginningDateTime = currentDateTime
                .minusHours(currentDateTime.getHour())
                .minusMinutes(currentDateTime.getMinute())
                .minusSeconds(currentDateTime.getSecond())
                .minusNanos(currentDateTime.getNano());
        repository.deleteByCreationDateIsNullOrCreationDateBefore(dayBeginningDateTime);

        //  Check if already exists a shortened key for originalUrl
        ShortenedUrl foundShortened = repository.findByOriginalUrl(originalUrl);
        if (foundShortened != null) {
            return foundShortened.getShorteningKey();
        }

        // Generates a new and unique shortening key
        long randomID = rng.nextLong(Long.MAX_VALUE / 2, Long.MAX_VALUE);
        String shorteningKey = encoder.encode(randomID);
        while(repository.existsByShorteningKey(shorteningKey)) {
            randomID = rng.nextLong();
            shorteningKey = encoder.encode(randomID);
        }

        if (whitelist.getOrDefault(originalUrl, false)) {
            ShortenedUrl shortened = new ShortenedUrl();
            shortened.setOriginalUrl(originalUrl);
            shortened.setShorteningKey(shorteningKey);
            repository.save(shortened);
        }

        return shorteningKey;
    }

    public String redirectFor(String shorteningKey) {
        ShortenedUrl foundShortened = repository.findByShorteningKey(shorteningKey);
        return foundShortened != null ? foundShortened.getOriginalUrl() : "";
    }

    public List<ShortenedUrl> listAll() {
        return repository.findAll();
    }

    public boolean delete(String shorteningKey) {
        ShortenedUrl shortened = repository.findByShorteningKey(shorteningKey);
        boolean keyExistsFlag = shortened != null;
        if (keyExistsFlag) repository.delete(shortened);
        return keyExistsFlag;
    }

    public HashMap<String, Boolean> getWhitelist() {
        return whitelist;
    }

}
