package com.test.urlshortener.core;

public class UrlShortenerCore {

    private static final String BASE62_ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public static String encodeURL(String originUrl){
        long n = originUrl.hashCode();
        boolean isNegative = n < 0;

        n = Math.abs(n);

        StringBuilder ret = new StringBuilder();

        while (n > 0) {
            long idx = n % 62;
            ret.insert(0, BASE62_ALPHABET.charAt((int) idx));
            n = (n / 62);
        }

        return (isNegative ? "0" : "1") + ret.toString();
    }
}
