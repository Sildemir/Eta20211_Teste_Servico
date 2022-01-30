import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import models.Produto;
import models.User;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static Requests.Produto_Endpoints.*;
import static Requests.User_Endpoints.*;
//import static Requests.User_Endpoints.getUserRequest;
//import static Requests.User_Endpoints.postUserRequest;
import static org.hamcrest.Matchers.*;

public class GetProdutoTest extends TestBaseProduto {
    private Produto valideProduto2;
    private Produto valideProduto1;
    @BeforeClass
    public void genrateTestData(){
        valideProduto1 = new Produto("caneta","5","esferografica","10");
        postProdutoRequest(SPEC,valideProduto1);
        valideProduto2 = new Produto("lapis","1","cinza","20");
        postProdutoRequest(SPEC,valideProduto2);
    }

    @DataProvider(name = "userQueryData")
    public Object[][] createQueryData(){
        return new Object [][]{
                {"nome", valideProduto1.nome},
                {"quantidade", valideProduto2.quantidade}
        };
    }

    @Test
    public void SholdreturnAllProdutosAndAtatusCode400 () {
        Response getProdutosResponse = getProdutoRequest(SPEC);
        getProdutosResponse.
                then().
                assertThat().
                statusCode(200).
                body("quantidade",equalTo(2)).
                body("quantidade",instanceOf(Integer.class)).
                body("produtos",instanceOf(List.class));
    }
    @Test(dataProvider = "userQueryData")
    public void SholdreturnProdutoForQueryAndAtatusCode200 (String query,String queryValue) {
        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .addHeader("accept","application/json")
                .setBaseUri("\"http://localhost:3000\"")
                .addQueryParam(query,queryValue).build();

        Response getProdutosResponse = getProdutoRequest(SPEC);
        getProdutosResponse.then().log().all();

        FilterableRequestSpecification filterableRequestSpecification = (FilterableRequestSpecification) SPEC;
        filterableRequestSpecification.removeQueryParam(query);


    }

   /* @AfterClass
    public void removeTestData(){
        deleteProdutoRequest(SPEC,valideProduto1);
        deleteProdutoRequest(SPEC,valideProduto2);

    }*/



}
