package com.tryitout.bootgql;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import com.netflix.graphql.dgs.DgsQueryExecutor;
import com.tryitout.bootgql.graphql.CryptoTickerDataFetcher;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CryptoAPITest {

  @Autowired
  CryptoTickerDataFetcher cryptoFetcher;

  @Autowired
  DgsQueryExecutor dgsQueryExecutor;

  @Test
  void allTickerValues() {
    String query = """
        query {
          allTickerValues {
              symbol
              volume
            }
          }""";

    List<String> symbols = dgsQueryExecutor.executeAndExtractJsonPath(
        query,
        "data.allTickerValues[*].symbol");

    assertTrue(symbols.contains("NEOBTC"));
  }

  @Test
  void addTowatchList() {

    String mutation = """
        mutation {
          addToWatchList(symbol: \"EOSETH\")
        }""";

    List<String> symbols = dgsQueryExecutor.executeAndExtractJsonPath(
        mutation,
        "data.addToWatchList[*]");
    assertTrue(symbols.contains("EOSETH"));
  }

  @Test
  void allMyTickerValues() {
    String query = """
        query {
          allMyTickerValues {
              symbol
              volume
            }
          }""";

    List<String> symbols = dgsQueryExecutor.executeAndExtractJsonPath(
        query,
        "data.allMyTickerValues[*].symbol");

    assertTrue(symbols.contains("EOSETH"));
  }

  @Test
  void thisTickerValue() {
    String query = """
        query {
          thisTickerValue(symbol: \"LTCBTC\") {
            symbol
            volume
          }
        }""";

    List<String> symbols = dgsQueryExecutor.executeAndExtractJsonPath(
        query,
        "data.thisTickerValue[*].symbol");
    assertTrue(symbols.contains("LTCBTC"));
  }

  @Test
  void watchList() {
    this.addTowatchList();

    String query = """
        query {
          watchList
          }""";

    List<String> symbols = dgsQueryExecutor.executeAndExtractJsonPath(
        query,
        "data.watchList");
    assertTrue(symbols.contains("EOSETH"));
  }

  @Test
  void removeFromWatchList() {

    addTowatchList();

    String mutation = """
        mutation {
          removeFromWatchList(symbol: \"EOSETH\")
        }""";

    List<String> symbols = dgsQueryExecutor.executeAndExtractJsonPath(
        mutation,
        "data.removeFromWatchList[*]");

    assertFalse(symbols.contains("EOSETH"));
  }
}