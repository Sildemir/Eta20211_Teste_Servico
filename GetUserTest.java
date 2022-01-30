import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import models.User;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static Requests.User_Endpoints.*;
//import static Requests.User_Endpoints.getUserRequest;
//import static Requests.User_Endpoints.postUserRequest;
import static org.hamcrest.Matchers.*;

public class GetUserTest extends TestBase{
        private User valideUser2;
        private User valideUser1;
        @BeforeClass
        public void genrateTestData(){
            valideUser1 = new User("ANA","francisco@gmail13.com","123@A","false");
            postUserRequest(SPEC,valideUser1);
            valideUser2 = new User("hugo","hugo@gmail12.com","123@A","false");
            postUserRequest(SPEC,valideUser2);
        }

    @DataProvider(name = "userQueryData")
            public Object[][] createQueryData(){
                return new Object [][]{
                        {"nome", valideUser1.nome},
                        {"email", valideUser2.email}
                };
            }




    @Test
    public void SholdreturnAllUsersAndAtatusCode200 () {
        Response getUsersResponse = getUserRequest(SPEC);
        getUsersResponse.
                then().
                    assertThat().
                    statusCode(200).
                    body("quantidade",equalTo(22)).
                    body("quantidade",instanceOf(Integer.class)).
                   body("usuarios",instanceOf(List.class));
}
    @Test(dataProvider = "userQueryData")
    public void SholdreturnUserForQueryAndAtatusCode200 (String query,String queryValue) {
        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .addHeader("accept","application/json")
                .setBaseUri("\"http://localhost:3000\"")
                .addQueryParam(query,queryValue).build();

        Response getUsersResponse = getUserRequest(SPEC);
        getUsersResponse.then().log().all();

        FilterableRequestSpecification filterableRequestSpecification = (FilterableRequestSpecification) SPEC;
        filterableRequestSpecification.removeQueryParam(query);


    }
        @AfterClass
        public void removeTestData(){
            deleteUserRequest(SPEC,valideUser1);
            deleteUserRequest(SPEC,valideUser2);

        }

    
}
