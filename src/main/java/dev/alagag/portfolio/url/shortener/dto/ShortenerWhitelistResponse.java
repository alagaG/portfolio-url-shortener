package dev.alagag.portfolio.url.shortener.dto;

import java.util.HashMap;

public record ShortenerWhitelistResponse(HashMap<String, Boolean> whitelist) {
}
