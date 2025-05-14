package dev.alagag.portfolio.url.shortener.dto;

import dev.alagag.portfolio.url.shortener.entity.ShortenedUrl;

import java.util.List;

public record ShortUrlListResponse(List<ShortenedUrl> shortenedUrls) {


}
