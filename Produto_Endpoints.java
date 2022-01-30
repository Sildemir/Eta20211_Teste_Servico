package Requests;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import com.google.gson.Gson;
import models.Produto;
import models.User;
import static io.restassured.RestAssured.given;

public class Produto_Endpoints extends RequestBaseProduto {
    public static Response getProdutoRequest(RequestSpecification spec) {

        Response getProdutoResponse =
                given().
                        spec(spec).

                        when().
                        get("/produtos");
        return getProdutoResponse;
    }


    public static Response postProdutoRequest(RequestSpecification spec, Produto produto) {
        Gson gson = new Gson();
        String userJsonRepresentation = gson.toJson(produto);

        Response postProdutoResponse =
                given().
                        spec(spec).
                        header("Content-Type", "application/json").
                        and().
                        body(userJsonRepresentation).
                        when().
                        post("/produtos");
        produto.setProdutoId(getValueFromResponse(postProdutoResponse,"_id"));

        return postProdutoResponse;
    }


  /*  public static Response deleteProdutoRequest(RequestSpecification spec,Produto produto) {

        Response deleteProdutoResponse =
                given().
                        spec(spec).
                        pathParam("_id",produto._id).

                        when().
                        delete("/produtos/{_id}");

        return deleteProdutoResponse;
    }*/


}
