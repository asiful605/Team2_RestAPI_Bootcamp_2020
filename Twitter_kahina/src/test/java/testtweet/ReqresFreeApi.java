package testtweet;

import io.restassured.http.ContentType;
import org.json.JSONObject;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class ReqresFreeApi {


    protected String baseUri;

    public ReqresFreeApi () {
        this.baseUri = "https://reqres.in/";
    }
    //Post method
    @Test(enabled =true, priority = 17)
    public void testCreateUser1(){
        JSONObject request=new JSONObject();
        request.put("name","kahina");
        request.put("job","tester");
        request.put("id","13");
        given().
                header("Content-Type","application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toString()).
                when().
                post("https://reqres.in/api/users/1").
                then().
                statusCode(201);
    }
      //Put method
    @Test(enabled =false, priority = 18)
    public void testUpdateUser1(){
        JSONObject request=new JSONObject();
        request.put("name","kahina");
        request.put("job","QA tester");
        given().
                header("Content-Type","application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toString()).
                when().
                post("https://reqres.in/api/users/1").
                then().
                statusCode(201);
    }
    //Patch method
    @Test(enabled =true, priority = 19)
    public void testPatchUser1(){
        JSONObject request=new JSONObject();
        request.put("name","kahina");
        request.put("job","QA_tester");
        given().
                header("Content-Type","application/json/1").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toString()).
                when().
                post("https://reqres.in/api/users/1").
                then().
                statusCode(201);
    }
    //Delete method
    @Test(enabled = true,priority = 20)
    public void testDeleteUser1(){
        given().
                delete("https://reqres.in/api/users/1").
                then().
                statusCode(204).log().all();
    }
    //Get methods
    @Test(enabled = true,priority = 21)
    public void testGetUnknowninfo(){
        given().
                get("https://reqres.in/api/unknown/2").
                then().
                statusCode(200) .log().all();
    }
}
