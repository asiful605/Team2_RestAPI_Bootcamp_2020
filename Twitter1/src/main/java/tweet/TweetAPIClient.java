package tweet;

import base.TwitterAPIClient;
import io.restassured.response.Response;
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

    public Response sendTweet(String s) {
        return null;
    }

    public Response searchProfile(String nafiszohaqa) {
        return null;
    }

    public Response sentTweet(String s) {
        return null;
    }
}
