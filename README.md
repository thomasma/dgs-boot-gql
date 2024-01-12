# Read Me First
This app will start a [Netflix-DGS Graphql server](https://github.com/Netflix/dgs-framework) (running on Spring Boot) and serve Graphql requests at http://localhost:8080/graphiql

The service calls an external API hosted by Binance at [Binance APIs](https://api2.binance.com/api/v3/ticker/24hr). This API returns last 24hr ticker price change stats for 100s of crypto currencies. 

* For reference see [Binance 24hr ticker price change statistics](https://github.com/binance/binance-spot-api-docs/blob/master/rest-api.md#24hr-ticker-price-change-statistics)


Steps to run the example...

```
* mvn test => to run the basic tests (the tests do not use mocks as i actually wanted to invoke the external API)
* mvn clean spring-boot:run => to run the app with DGS ready to serve graphql queries/mutations
* http://localhost:8080/graphiql => to get to the graphical query editor
```

## A few queries/mutations for you to test

Retrieve specific ticker...
```
query {
  thisTickerValue(symbol: "LTCBTC") {
    symbol
    priceChange
    priceChangePercent
    volume
  }
}
```


Add to my ticker watchlist...
```
mutation ($symbol: String!) {
  addToWatchList(symbol: $symbol)
}
```

Retrive my ticker watch list...
```
query {
   watchList 
}
```

Retrieve data for all tickers...
```
{
  allTickerValues {
    symbol
    volume
  }
}
```

Retrieve data for tickers in my watch list...
```
{
  allMyTickerValues {
    symbol
    volume
  }
}
```

Delete ticker from my watch list...
```
mutation ($symbol: String!) {
  removeFromWatchList(symbol: $symbol)
}
```
end..
