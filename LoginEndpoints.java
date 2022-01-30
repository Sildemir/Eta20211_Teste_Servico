package Requests;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.Produto;
import models.User;


import static io.restassured.RestAssured.given;

public class LoginEndpoints {

    public static Response postLoginRequest(RequestSpecification spec, User user){
        Response postLoginResponse =
                given().
                        spec((spec)).
                        header("Content-Type","application/json").
                        and().
                        body(user.getUserCredentials()).
                        log().all().
                when().
                        post("/login");
        return postLoginResponse;

    }
}
