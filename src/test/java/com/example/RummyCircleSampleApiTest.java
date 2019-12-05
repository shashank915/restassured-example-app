package com.example;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class RummyCircleSampleApiTest {

    private static final String ROOT_URI = "http://node.games24x7.com/fms/join";


    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "http://node.games24x7.com/fms/join";
        RestAssured.port = 80;
    }


    /**
     * This test case includes only simple api call. To test all types of response types(scenarios), create separate methods
     * containing different request bodies.
     */
    @Test
    public void sampleJoinTest(){

        Map<String,Object> headers = new HashMap();
        String serviceId = "1";

        headers.put("uniqueAPIIdentifier","Some Value");
        headers.put("isRetry",true);


        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .headers(headers)
                .body("{\n" +
                        "\t\"value\":{\n" +
                        "\t\t\"userId\": 100,\n" +
                        "\t\t\"amount\": 75.00,\n" +
                        "\t\t\"serviceChargeAmount\": 2,\n" +
                        "\t\t\"gstEnabled\": true,\n" +
                        "\t\t\"jointype\": 1,\n" +
                        "\t\t\"toumid\": 123456,\n" +
                        "\t\t\"prizetype\": 1,\n" +
                        "\t\t\"gamevariant\": 1,\n" +
                        "\t\t\"matchid\": 7323,\n" +
                        "\t\t\"rpjoinamount\": 1000,\n" +
                        "\t\t\"minbuyin\": 80.0,\n" +
                        "\t\t\"channelId\": 3\n" +
                        "\t},\n" +
                        "\t\"context\":{\n" +
                        "\t\t\"source\": \"RC\"\n" +
                        "\t}\n" +
                        "}")
                .when()
                .post(ROOT_URI + serviceId);

        System.out.println("Server Response :: " + response.asString());

        //tests
        //to check the status code
        response.then().statusCode(200);
        //check for other parameters
        response.then().body("amount", Matchers.is(200));
        response.then().body("serviceChargeAmount", Matchers.is(2));

    }
}
