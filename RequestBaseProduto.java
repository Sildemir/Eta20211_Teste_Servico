package Requests;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class RequestBaseProduto {
    public static String getValueFromResponse(Response response,String value){
        JsonPath jsonPathEvaluator = response.jsonPath();
        return jsonPathEvaluator.get(value);
    }

}
