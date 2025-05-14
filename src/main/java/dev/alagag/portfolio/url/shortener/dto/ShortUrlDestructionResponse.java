package dev.alagag.portfolio.url.shortener.dto;

public record ShortUrlDestructionResponse(String message) {

    public static ShortUrlDestructionResponse forSuccess() {
        return new ShortUrlDestructionResponse("Link deleted successfully!");
    }

    public static ShortUrlDestructionResponse forFailure() {
        return new ShortUrlDestructionResponse("Failed trying to delete this link!");
    }

}
