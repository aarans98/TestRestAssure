package com.example.demo;

import com.example.demo.entity.Student;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.Test;

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
    @Feature("Testing GET method by id")
    @Severity(SeverityLevel.CRITICAL)
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
    @Feature("Validate response response of POST method")
    @Severity(SeverityLevel.CRITICAL)
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
    @Feature("Response time GET methhod")
    @Severity(SeverityLevel.CRITICAL)
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

    @Test
    @Feature("Validate response body")
    @Severity(SeverityLevel.CRITICAL)
    public void testGetAll() {
        ValidatableResponse body = when()
                .get("student/getAll")
                .then()
                .statusCode(200)
//                .assertThat()
                .contentType(ContentType.JSON)
                .body("name",hasItem("Ananda Aransa"),
                        "major",hasItem("Physics"));
    }

    @Test
    @Feature("Validate response body GET by id method")
    @Severity(SeverityLevel.CRITICAL)
    public void testStudentInformation() {
                given().get("student/getById/{id}",1)
                .then()
                .statusCode(200)
                .assertThat()
                .body("name",equalTo("Ananda Aransa"))
                .body("major",equalTo("Physics"));
    }

    @Test
    @Feature("Invalid URL")
    @Severity(SeverityLevel.MINOR)
    public void test404() {
        given().get("student/geti")
                .then()
                .statusCode(404)
                .contentType(ContentType.JSON);
    }

}
