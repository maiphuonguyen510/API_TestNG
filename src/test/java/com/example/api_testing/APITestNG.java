package com.example.api_testing;

import io.restassured.http.ContentType;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class APITestNG  {
    @Test(description = "To get the details of product with id 3", priority = 0)
    public void verifyUser() {

        // Given
        given()

                // When
                .when()
                .get("https://reqres.in/api/products/3?id=3")

                // Then
                .then()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                // To verify booking id at index 3
                .body("data.name", equalTo("true red"))
                .body("data.id", equalTo(3));
    }

    @Test(description = "To create a new employee", priority = 1)
    public void createUser() {

        JSONObject data = new JSONObject();

        data.put("employee_name", "APITest");
        data.put("employee_salary", "99999");
        data.put("employee_age", "30");


        // GIVEN
        given()
                .baseUri("https://dummy.restapiexample.com/api")
                .contentType(ContentType.JSON)
                .body(data.toString())

                // WHEN
                .when()
                .post("/v1/create")

                // THEN
                .then()
                .statusCode(200)
                .body("data.employee_name", equalTo("APITest"))
                .body("message", equalTo("Successfully! Record has been added."));

    }
}
