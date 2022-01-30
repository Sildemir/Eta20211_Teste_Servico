package Requests;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.Produto;
import models.User;
import static Requests.LoginEndpoints.postLoginRequest;


import static io.restassured.RestAssured.given;

public class LoginProdutoEndpoints {
    public static Response postLoginRequest(RequestSpecification spec, Produto produto){
        Response postLoginResponse =
                given().
                        spec((spec)).
                        header("Content-Type","application/json").
                        and().
                        body(produto.getProdutoQuantidade()).
                        log().all().
                        when().
                        post("/login");
        return postLoginResponse;

    }

}
