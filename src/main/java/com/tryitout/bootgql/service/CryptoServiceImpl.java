package com.tryitout.bootgql.service;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.tryitout.bootgql.domain.Crypto;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CryptoServiceImpl implements CryptoService {
    /** private internal state only for dev poc */
    private Set<String> watchlist = new TreeSet<String>();

    /**
     * get all crypto quotes
     */
    public List<Crypto> allTickerValues() {
        return getCryptoQuotes();
    }

    public List<String> watchList() {
        return watchlist.stream().collect(Collectors.toList());
    }

    /**
     * Adds a crypto symbol to my watch list.
     * 
     * @param symbol
     * @return
     */
    public List<String> addToWatchList(String symbol) {
        if (symbol != null) {
            watchlist.add(symbol);
        }

        return watchlist.stream().collect(Collectors.toList());
    }

    /**
     * Removes a crypto symbol from my watch list.
     * 
     * @param symbol
     * @return
     */
    public List<String> removeFromWatchList(String symbol) {
        if (symbol != null) {
            watchlist.remove(symbol);
        }

        return watchlist.stream().collect(Collectors.toList());
    }

    public List<Crypto> getCryptoQuotes() {
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl = "https://api2.binance.com/api/v3/ticker/24hr";

        ResponseEntity<List<Crypto>> response = restTemplate.exchange(resourceUrl, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Crypto>>() {
                });

        return response.getBody();
    }
}