package com.example.fintestq.controller;

import com.example.fintestq.model.YahooFullStockData;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

//import static io.restassured.RestAssured.given;
//import static org.hamcrest.Matchers.*;

@QuarkusTest
public class StockControllerIntegrationTest {

//  @Test
//  public void testGetStockData() {
//    // Arrange: Define the stock symbol to test
//    String symbol = "AAPL";
//
//    // Act & Assert: Perform the HTTP GET request and verify the response
//    given()
//            .pathParam("symbol", symbol)
//            .when()
//            .get("/stocks/{symbol}")
//            .then()
//            .statusCode(200)
//            .body("name", notNullValue(),
//                    "symbol", is("AAPL"),
//                    "price", greaterThan(0.0f),
//                    "change", notNullValue());
//  }
}