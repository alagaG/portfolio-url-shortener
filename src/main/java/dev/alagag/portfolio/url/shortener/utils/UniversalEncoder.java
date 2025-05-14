package dev.alagag.portfolio.url.shortener.utils;

public class UniversalEncoder {
    private String charset;

    public UniversalEncoder(String charset) {
        this.charset = charset;
    }

    public String encode(long value) {
        int base = getBase();
        StringBuilder builder = new StringBuilder();
        while(value > 0) {
            builder.append(charset.charAt((int) (value % base)));
            value /= base;
        }
        return builder.reverse().toString();
    }

    public long decode(String value) {
        int base = getBase();
        long result = 0L;
        int charCount = value.length();
        for (int i = 0; i < charCount; i++) {
            result = (result * base) + charset.indexOf(value.charAt(i));
        }
        return 0L;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public int getBase() {
        return charset.length();
    }

}
