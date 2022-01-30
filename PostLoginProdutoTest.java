import io.restassured.response.Response;
import models.Produto;
import models.User;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static Requests.LoginEndpoints.postLoginRequest;
import static Requests.Produto_Endpoints.postProdutoRequest;
import static Requests.User_Endpoints.deleteUserRequest;
import static Requests.User_Endpoints.postUserRequest;
import static org.hamcrest.Matchers.*;

public class PostLoginProdutoTest extends TestBaseProduto {
    private Produto valideProduto1;
    private Produto valideProduto2;
    @BeforeClass
    public void genrateTestData(){
        valideProduto1 = new Produto("carro","1000","gol","2");
        postProdutoRequest(SPEC,valideProduto1);
        valideProduto2 = new Produto("car","100","fiat","4");
        postProdutoRequest(SPEC,valideProduto2);

    }

    @Test
    public void SholdreturnSucessMensageAndAtatusCode201(){
        Response loginProdutoResponse = postProdutoRequest(SPEC,valideProduto1);
        loginProdutoResponse.
                then().
                log().all().
                statusCode(201).
                body("message",equalTo("Cadastrado realizado com sucesso")).
                body("_id",equalTo("jogfODIlXsqxNFS2")).
                body("_id",nullValue());
    }
    @Test
    public void SholdreturnSucessMensageAndAtatusCode403(){
        Response loginProdutoResponse = postProdutoRequest(SPEC,valideProduto2);
        loginProdutoResponse.
                then().
                assertThat().
                statusCode(403).
                body("message",equalTo("Rota exclusiva para administradores")).
                body("_id",equalTo("jogfODIlXsqxNFS2")).
                body("authorization",nullValue());
    }


}
