package tweet;

import base.TwitterAPIClient;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class TweetAPIClient extends TwitterAPIClient {

    private final String CREATE_TWEET_ENDPOINT="/statuses/update.json";
    private final String DELETE_TWEET_ENDPOINT="/statuses/destroy.json";
    private final String GET_USER_TWEET_ENDPOINT="/statuses/user_timeline.json";
    protected final static String BASE_URI ="https://api.twitter.com/1.1";
    protected final static String TWEET_ENDPOINT ="/statuses/update.json";
    protected final static String SEARCH_ENDPOINT ="/users/search.json";

    // GET All tweet information
    public ValidatableResponse getUserTimelineTweets(){
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUri+this.GET_USER_TWEET_ENDPOINT)
                .then();
    }
    // Create a tweet from users twitter
    public ValidatableResponse createTweet(String tweet){
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("status",tweet)
                .when().post(this.baseUri+this.CREATE_TWEET_ENDPOINT)
                .then();
    }
    // Delete a tweet from users twitter
    public ValidatableResponse deleteTweet(Long tweetId){
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id",tweetId)
                .when().post(this.baseUri+this.DELETE_TWEET_ENDPOINT)
                .then();
    }
    public ValidatableResponse sentTweet(String status){
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("status",status).when().post(BASE_URI + TWEET_ENDPOINT).then()
                .statusCode(200);
    }
    public ValidatableResponse searchProfile(String q){
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("q",q).when().get(BASE_URI + SEARCH_ENDPOINT).then()
                .statusCode(200);
    }
}
