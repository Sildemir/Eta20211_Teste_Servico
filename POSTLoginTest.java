import io.restassured.response.Response;
import models.User;
import org.apache.http.HttpStatus;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static Requests.LoginEndpoints.postLoginRequest;
import static Requests.User_Endpoints.deleteUserRequest;
import static Requests.User_Endpoints.postUserRequest;
import static org.hamcrest.Matchers.*;



public class POSTLoginTest extends TestBase{
    private User valideUser1;

    @BeforeClass
    public void genrateTestData(){
        valideUser1 = new User("ANA","francisco@gmail13.com","123@A","false");
        postUserRequest(SPEC,valideUser1);

    }
        @Test
        public void SholdreturnSucessMensageAndAtatusCode200(){
            Response loginUserResponse = postLoginRequest(SPEC,valideUser1);
            loginUserResponse.
                    then().
                            assertThat().
                            statusCode(HttpStatus.SC_OK).
                            body("message",equalTo("Login realizado com sucesso")).
                            log().all().
                            body("authorization",notNullValue());
        }



    @AfterClass
    public void removeTestData(){
        deleteUserRequest(SPEC,valideUser1);


    }


}
