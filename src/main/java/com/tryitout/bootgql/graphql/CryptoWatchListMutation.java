package com.tryitout.bootgql.graphql;

import java.util.List;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.InputArgument;
import com.tryitout.bootgql.service.CryptoService;

import org.springframework.beans.factory.annotation.Autowired;

@DgsComponent
public class CryptoWatchListMutation {

    @Autowired
    CryptoService cryptoService;

    @DgsMutation
    public List<String> addToWatchList(@InputArgument String symbol) {
        return cryptoService.addToWatchList(symbol);
    }

    @DgsMutation
    public List<String> removeFromWatchList(@InputArgument String symbol) {
        return cryptoService.removeFromWatchList(symbol);
    }
}
