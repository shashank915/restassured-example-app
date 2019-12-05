package com.example;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.hasItems;

public class SampleTest {
    final static String ROOT_URI = "http://localhost:3000/employees";

    @Test
    public void sample_get_Test(){
        Response response = get(ROOT_URI);
        System.out.println(response.asString());

        response.then().body("id", hasItems(1,2));
        response.then().body("name", hasItems("Pankaj"));
    }

    @Test
    public void sample_post_Test(){

        String employeeStr = "{\"id\": \"9\",\"name\": \"Lisa\",\"salary\": \"2000\"}";
        Response response = given().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(employeeStr)
                .when()
                .post(ROOT_URI );
        System.out.println("POST response:: " + response.asString());

        //test
//        response.then().body("id", Matchers.is(Integer.valueOf(8)));
        response.then().body("name", Matchers.is("Lisa"));
    }

    @Test
    public void sample_put_request(){

        String employeeStr = "{\"id\": \"9\",\"name\": \"Lisa\",\"salary\": \"5000\"}";
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(employeeStr)
                .when()
                .put(ROOT_URI + "/3");

        System.out.println(response.asString());

        //tests
//        response.then().body("id", Matchers.is(3));
        response.then().body("name", Matchers.is("Lisa"));
        response.then().body("salary", Matchers.is("5000"));
    }

    @Test
    public void sample_delete_test(){
        Response response = delete(ROOT_URI + "/4");

        System.out.println(response.asString());

        //tests
        response = get(ROOT_URI );
        System.out.println(response.asString());
        response.then().body("id", Matchers.not(4));
    }
}
