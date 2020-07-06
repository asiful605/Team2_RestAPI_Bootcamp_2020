package testtweet;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tweet.TweetAPIClient;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import static com.google.common.base.Predicates.equalTo;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;


public class TestTweetAPIClient {
    private TweetAPIClient tweetAPIClient;
    public String baseUrl = "http://info.venturepulse.org:8080/service-webapp";

    @BeforeClass
    public void setUpTweetAPI() {
        this.tweetAPIClient = new TweetAPIClient();
    }

    @Test(enabled = true)
    public void testUserCanTweetSuccessfully() {
        // 1. User send a tweet
        String tweet = "We can tweet All Day " + UUID.randomUUID().toString();
        ValidatableResponse response = this.tweetAPIClient.createTweet(tweet);
        // 2. Verify that the tweet was successfully send
        response.statusCode(200);
        String actualTweet = response.extract().body().path("text");
        Assert.assertEquals(tweet, actualTweet);
    }


    @Test
    public void testUserCanAgainDoTweetSuccessfully() {
        Response response = tweetAPIClient.sentTweet("Tweeting from Queens,New York");
        response.getBody().prettyPrint();
        int statusCode = response.getStatusCode();
        String statusLine = response.getStatusLine();
        Assert.assertEquals(statusCode, 200);
        Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");

    }

    @Test
    public void testUserCannnotTweetDuplicateTweet() {
        Response response = tweetAPIClient.sentTweet("Hey!This is Shamima" + UUID.randomUUID());
        response.getBody().prettyPrint();
        int statusCode = response.getStatusCode();
        String statusLine = response.getStatusLine();
        Assert.assertEquals(statusCode, 200);
        Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
    }

    @Test
    public void testUserCanSearchProfileSuccessfully() {
        Response response = tweetAPIClient.searchProfile("sarya@9449");
        response.getBody().prettyPrint();
        int statusCode = response.getStatusCode();
        String statusLine = response.getStatusLine();
        Assert.assertEquals(statusCode, 200);
        Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
    }

    @Test
    public void testUser2CanSearchProfileSuccessfully() {
        Response response = tweetAPIClient.searchProfile("sarya@9449");
        response.getBody().prettyPrint();
        int statusCode = response.getStatusCode();
        String statusLine = response.getStatusLine();
        Assert.assertEquals(statusCode, 200);
        Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
    }

    // Write an API test where user cannot tweet the same tweet twice in a row
    @Test
    public void testUserCanNotTweetTheSameTweetTwiceInARow() {
        // 1. User send a tweet
        String tweet = "Tweet " + UUID.randomUUID().toString();
        ValidatableResponse response = this.tweetAPIClient.createTweet(tweet);
        // 2. Verify that the tweet was successfully sent
        response.statusCode(200);
        String actualTweet = response.extract().body().path("text");
        Assert.assertEquals(tweet, actualTweet);
        // 3. User sends the same tweet again
        response = this.tweetAPIClient.createTweet(tweet);
        // 4. Verify that the tweet was unsuccessfull
        response.statusCode(403);
        String expectedMessage = "Status is a duplicate.";
        String actualMessage = response.extract().body().path("errors[0].message");
        Assert.assertEquals(actualMessage, expectedMessage);
    }

    @Test
    public void testDeleteTweet() {
        String tweet = "Tweet " + UUID.randomUUID().toString();
        // ValidatableResponse response=this.tweetAPIClient.createTweet(tweet).assertThat()
        // .body("id",equals());
        ValidatableResponse response = this.tweetAPIClient.deleteTweet(1277026648693538821l);

    }

    @Test(enabled = false)
    public void testUserFalseData() {
//        Map<String,Object> map=new HashMap<>();
//        map.put("name","shamima");
//        map.put("job","QA");
//        System.out.println();
        JSONObject request = new JSONObject();
        request.put("name", "shamima");
        request.put("job", "QA");
        System.out.println(request);
        given().
                header("Content-Type", "application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toString()).
                when().
                post("https://reqres.in/api/users").
                then().
                statusCode(201).log().all();
    }

    @Test(enabled = false)
    public void testUserPost() {
        JSONObject request = new JSONObject();
        request.put("name", "demo");
        request.put("job", "SDET");
        given().
                header("Content-Type", "application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toString()).
                when().
                post("https://reqres.in/api/users").
                then().
                statusCode(201);
    }

    @Test(enabled = false)
    public void testCasePost() {
        JSONObject request = new JSONObject();
        request.put("name", "david");
        request.put("job", "qa Lead");
        given().
                header("Content-Type", "application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toString()).
                when().
                post("https://reqres.in/api/users").
                then().
                statusCode(201);
    }

    // put method
    @Test(enabled = false)
    public void test_Case_Post() {
        JSONObject request = new JSONObject();
        request.put("name", "david");
        request.put("job", "qa Lead");
        given().
                header("Content-Type", "application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toString()).
                when().
                put("https://reqres.in/api/users/2").
                then().
                statusCode(200).log().all();
    }

    @Test(enabled = false)
    public void testCaseUsePost() {
        JSONObject request = new JSONObject();
        request.put("name", "demo");
        request.put("job", "SDET");
        given().
                header("Content-Type", "application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toString()).
                when().
                put("https://reqres.in/api/users/2").
                then().
                statusCode(200).log().all();
    }

    // patch method
    @Test(enabled = false)
    public void testCaseUserPost() {
        JSONObject request = new JSONObject();
        request.put("name", "david");
        request.put("job", "qa Lead");
        given().
                header("Content-Type", "application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toString()).
                when().
                patch("https://reqres.in/api/users/2").
                then().
                statusCode(200).log().all();
    }

    @Test(enabled = false)
    public void test_Case_User_Post() {
        JSONObject request = new JSONObject();
        request.put("name", "demo");
        request.put("job", "SDET");
        given().
                header("Content-Type", "application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toString()).
                when().
                patch("https://reqres.in/api/users/2").
                then().
                statusCode(200).log().all();
    }

    @Test(enabled = false)
    public void test_Case_UserPost() {
        JSONObject request = new JSONObject();
        request.put("name", "shamima");
        request.put("job", "QA");
        System.out.println(request);
        given().
                header("Content-Type", "application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toString()).
                when().
                patch("https://reqres.in/api/users/2").
                then().
                statusCode(200).log().all();
    }

    // delete method
    @Test
    public void test_Case_Post_Delete() {
        given().
                delete("https://reqres.in/api/users/2").
                then().
                statusCode(204).log().all();
    }

    @Test
    public void testCase_Rest_API() {
        Response response = RestAssured.get("https://reqres.in/api/users?page=2");
        System.out.println(response.asString());
        System.out.println(response.getBody());
        System.out.println(response.getStatusCode());
        System.out.println(response.getStatusLine());
        System.out.println(response.getHeader("content-type"));
        System.out.println(response.getTime());
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);

    }

    @Test(enabled = false)
    public void test_Case_Get() {
        given().
                header("content-Type", "application/json").
                get("https://reqres.in/api/users?page=2").
                then().statusCode(200).
                body("data.id[1]", (ResponseAwareMatcher<Response>) equalTo(8)).
                body("data.first_name", hasItems("Michael", "Lindsay"));
    }

    @Test(enabled = false)
    public void test_Case_User_Get() {
        given().
                get("https://reqres.in/api/users/2").
                then().statusCode(200).
                log().all();
    }

    @Test(enabled = false)
    public void test_case_Get_User() {
        given().
                get("https://reqres.in/api/users/23").
                then().statusCode(404).
                log().
                all();
    }

    @Test(enabled = false)
    public void test_Case_Get_Unknown() {
        given().
                get("https://reqres.in/api/unknown").
                then().statusCode(200).
                log().all();
    }

    @Test(enabled = false)
    public void test_Case_User_Unknown() {
        given().
                get("https://reqres.in/api/unknown/2").
                then().statusCode(200).log().all();
    }

    @Test(enabled = false)
    public void testCaseGet() {
        given().
                get("https://reqres.in/api/unknown/23").
                then().statusCode(404).log().all();
    }

    @Test
    public void testUserGet() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "shamima");
        map.put("job", "QA");
        System.out.println(map);

    }

    @Test
    public void handleAuthentication() {
        int code = RestAssured.given().auth().preemptive()
                .basic("ToolsQA", "TestPassword").when()
                .get("http://restapi.demoqa.com/authentication/CheckForAuthentication/")
                .getStatusCode();
        System.out.println("Response code; " + code);
    }

    @Test
    public void postTweet() {
        Response response = RestAssured.given().auth().oauth("vpV6NVcszP0n8XkxrQGIMq4yP",
                "zMQEM3kxlXOdXUU1dSJLi06vTKoUxJeapH6tuAE1PGmVtJLs1O",
                "1188045665471025155-eP1iDWPYnIoLEXpXeYYy1B4p8t8lEx",
                "Qdyl8sY5REtkZSog7A6wEUvE8JpH9bor1VOpwgCTbEbbK")
                .post("https://api.twitter.com/1.1/statuses/update.json?status= Developer ");
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody().asString());
        System.out.println(response.jsonPath().prettify());
        JsonPath jsonPath = response.jsonPath();
        String tweetID = jsonPath.get("id_str");
        System.out.println("The Tweet ID is: " + tweetID);

    }

    /*
   There are following public apis
#	Route	        Method  Type	                    Full route	                                Description	Details
1	/employee	    GET	    JSON	http://dummy.restapiexample.com/api/v1/employees	    Get all employee data Details
2	/employee/{id}	GET	    JSON	http://dummy.restapiexample.com/api/v1/employee/{id}	Get a single employee data Details
3	/create	        POST	JSON	http://dummy.restapiexample.com/api/v1/create	        Create new record in database Details
4	/update/{id}	PUT	    JSON	http://dummy.restapiexample.com/api/v1/update/{id}	    Update an employee record Details
5	/delete/{id}	DELETE	JSON	http://dummy.restapiexample.com/api/v1/delete/{id}	    Delete an employee record Details
*/
    @Test
    public void employeesTable() {
        //base URI
        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1/";
        //Request object
        RequestSpecification requestSpecification = RestAssured.given();
        //response object
        Response response = requestSpecification.request(Method.GET, "employees");
        //print response on Console
        String responseBody = response.getBody().asString();
        System.out.println("Response Body is; " + responseBody);
        //if the request is successful, you will get 200 as status code
        int statusCode = response.getStatusCode();
        System.out.println("status sode is: " + statusCode);
        Assert.assertEquals(statusCode, 200);
        //status line verification
        String statusLine = response.getStatusLine();
        System.out.println(statusLine);
        Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
    }

    @Test
    public void postTweets() {
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");

        JSONObject json = new JSONObject();
        json.put("id", "27");
        json.put("title", "java ");
        json.put("author", "john");

        request.body(json.toString());

        Response response = request.post("http://localhost:207/posts");
        int code = response.getStatusCode();
        Assert.assertEquals(code, 201);

    }

    @Test
    public void putTweet() {
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");

        JSONObject json = new JSONObject();
        json.put("id", "100");
        json.put("title", "java book");
        json.put("author", "sharmin");

        request.body(json.toString());

        Response response = request.put("https://twitter.com/home?lang=en");
        int code = response.getStatusCode();
        Assert.assertEquals(code, 200);

    }

    @Test
    public void deleteTweet() {
        RequestSpecification request = RestAssured.given();
        Response response = request.delete("http://localhost:207/posts/100");
        int code = response.getStatusCode();
        Assert.assertEquals(code, 200);
    }

    @Test
    public void callSingleEmployeeResources() {
        Response response = given().when().get("http://info.venturepulse.org:8080/service-webapp/api/SingleEmployeeResources/590a4acd35522970c7956cdf").then().statusCode(200).extract().response();
        String statusLine = response.getStatusLine();
        int statusCode = response.getStatusCode();
        String body = response.getBody().prettyPrint();
        Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
        Assert.assertEquals(statusCode, 200);
    }

    @Test
    public void callAllEmployeeResources() {
        Response response = given().when().get(baseUrl + "/api/AllEmployeeResources").then().statusCode(200).extract().response();
        String statusLine = response.getStatusLine();
        int statusCode = response.getStatusCode();
        String body = response.getBody().prettyPrint();
        Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
        Assert.assertEquals(statusCode, 200);
    }

    @Test
    public void badCallAllEmployeeResources() {
        Response response = null;
        int statusCode = 0;
        try {
            response = given().when().get(baseUrl + "/api/AllEmployeeResources809879");
            statusCode = response.getStatusCode();
        } catch (Exception ex) {
        }
        Assert.assertEquals(statusCode, 404);
    }







}
