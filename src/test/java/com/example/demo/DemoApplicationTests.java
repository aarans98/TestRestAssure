package com.example.demo;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;



@SpringBootTest
public class DemoApplicationTests {

    private static String requestBody = "{\n" +
            "  \"name\": \"Robby Kurnia\",\n" +
            "  \"major\": \"Physics\",\n" +
            "  \"gpa\": \"3.00\" \n}";
    @Test
    public void testController() {
        ValidatableResponse body = when()
                .get("student/getById/{id}", 1)
                .then()
                .statusCode(200)
                .body("id", equalTo(1),
                        "name", equalTo("Ananda Aransa"),
                        "major", equalTo("Physics"));
    }

    @Test
    public void postRequest() {
        Response response = given()
                .header("Content-type","application/json")
                .and()
                .body(requestBody)
                .when()
                .post("/student/post")
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("Robby Kurnia",response.jsonPath().getString("name"));
        Assertions.assertEquals("Physics",response.jsonPath().getString("major"));
        Assertions.assertEquals(7,response.jsonPath().getString("id"));
    }

    @Test
    public void whenMeasureResponseTime_thenOK() {
        Response response = RestAssured.get("student/getById/{id}",1);
        long timeInMS = response.time();
        long timeInS = response.timeIn(TimeUnit.SECONDS);
        System.out.println(timeInMS);
        Assertions.assertEquals(timeInS, timeInMS/1000);
    }

    @Test
    public void whenValidateResponseTime_thenSuccess() {
        when()
                .get("student/getById/{id}",1)
                .then()
                .time(lessThan(5000L));
    }

}
