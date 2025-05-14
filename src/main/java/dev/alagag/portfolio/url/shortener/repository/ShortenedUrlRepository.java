package dev.alagag.portfolio.url.shortener.repository;

import dev.alagag.portfolio.url.shortener.entity.ShortenedUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Repository
public interface ShortenedUrlRepository extends JpaRepository<ShortenedUrl, Long> {

    ShortenedUrl findByOriginalUrl(String originalUrl);

    ShortenedUrl findByShorteningKey(String shorteningKey);

    @Transactional
    void deleteByCreationDateIsNullOrCreationDateBefore(OffsetDateTime creationDate);

    boolean existsByShorteningKey(String shorteningKey);

}

