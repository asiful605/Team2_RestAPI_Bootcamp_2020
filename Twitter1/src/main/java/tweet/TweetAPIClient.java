package tweet;

import base.TwitterAPIClient;
import com.github.scribejava.core.model.Response;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class TweetAPIClient extends TwitterAPIClient {
    // http://developer.twitter.com/en/docs/tweets/timelines
    // Tweet Client class that consists of all the different API's of the Twitter tweet
    // POST: Retrieve and engage with tweets....

    //https://api.twitter.com/1.1/statuses/update.json
    private final String CREATE_TWEET_ENDPOINT="/statuses/update.json";
    //https://api.twitter.com/1.1/statuses/destroy/:id.json
    private final String DELETE_TWEET_ENDPOINT="/statuses/destroy.json";
    // https://api.twitter.com/1.1/statuses/user_timeline.json
    private final String GET_USER_TWEET_ENDPOINT="/statuses/user_timeline.json";

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
    protected final static String API_KEY ="A8voCKNv3nucoPHZ9VL8KVBrV";
    protected final static String API_SECTRET_KEY ="" +
            "BtKXb82tiuRfS64RRO7lkdTP4H23yFreBOVmYja8rr0Q1uOT7V";
    protected final static String ACCESS_TOKEN ="1184943022066356233-v2f7Dj6mw8aHHYRYbjb7f2IdxBSnP4";
    protected final static String ACCESS_TOKEN_SECRET= "edCY0WBhEmR9KN6ijpeotM9Ni7gsiQskjoZOvitMmAtS8";

    protected final static String BASE_URI ="https://api.twitter.com/1.1";
    protected final static String TWEET_ENDPOINT ="/statuses/update.json";
    protected final static String SEARCH_ENDPOINT ="/users/search.json";

    public Response sentTweet(String status){
        return (Response) given().auth().oauth(API_KEY,API_SECTRET_KEY,ACCESS_TOKEN,ACCESS_TOKEN_SECRET)
                .param("status",status).when().post(BASE_URI + TWEET_ENDPOINT).then()
                .statusCode(200).extract().response();
    }
    public Response searchProfile(String q){
        return (Response) given().auth().oauth(API_KEY,API_SECTRET_KEY,ACCESS_TOKEN,ACCESS_TOKEN_SECRET)
                .param("q",q).when().get(BASE_URI + SEARCH_ENDPOINT).then()
                .statusCode(200).extract().response();
    }
}
