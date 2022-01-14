package com.tryitout.bootgql.service;

import java.util.List;

import com.tryitout.bootgql.domain.Crypto;

public interface CryptoService {
    List<Crypto> allTickerValues();

    List<String> watchList();

    List<String> addToWatchList(String symbol);

    List<String> removeFromWatchList(String symbol);

}