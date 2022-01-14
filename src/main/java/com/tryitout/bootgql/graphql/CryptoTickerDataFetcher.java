package com.tryitout.bootgql.graphql;

import java.util.List;
import java.util.stream.Collectors;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import com.tryitout.bootgql.domain.Crypto;
import com.tryitout.bootgql.service.CryptoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@DgsComponent
public class CryptoTickerDataFetcher {

    private Logger logger = LoggerFactory.getLogger(CryptoTickerDataFetcher.class);

    @Autowired
    CryptoService cryptoService;

    @DgsQuery
    public List<Crypto> allTickerValues() {
        return cryptoService.allTickerValues().stream().collect(Collectors.toList());
    }

    @DgsQuery
    public List<Crypto> allMyTickerValues() {
        List<String> myWatchList = cryptoService.watchList();
        return cryptoService.allTickerValues().stream().filter(crypto -> myWatchList.contains(crypto.symbol()))
                .collect(Collectors.toList());
    }

    @DgsQuery
    public List<String> watchList() {
        return cryptoService.watchList();
    }

    @DgsQuery
    public List<Crypto> thisTickerValue(@InputArgument String symbol) {
        logger.info(">>>>>>> {}", symbol);
        return cryptoService.allTickerValues().stream().filter(crypto -> crypto.symbol().equals(symbol))
                .collect(Collectors.toList());
    }
}