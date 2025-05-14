package dev.alagag.portfolio.url.shortener.entity;

import jakarta.persistence.*;

import java.time.OffsetDateTime;

@Entity
public class ShortenedUrl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String originalUrl;
    @Column(unique = true)
    private String shorteningKey;
    private OffsetDateTime creationDate;

    public ShortenedUrl() {
        this.creationDate = OffsetDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getShorteningKey() {
        return shorteningKey;
    }

    public void setShorteningKey(String shorteningKey) {
        this.shorteningKey = shorteningKey;
    }

    public OffsetDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(OffsetDateTime creationDate) {
        this.creationDate = creationDate;
    }
}
