package tweet;

import base.TwitterAPIClient;
import io.restassured.response.ValidatableResponse;


import static io.restassured.RestAssured.given;

public class TweetAPIClient extends TwitterAPIClient {
    private final String CREATE_TWEET_ENDPOINT = "/statuses/update.json";
    private final String DELETE_TWEET_ENDPOINT = "/statuses/destroy.json";
    private final String GET_USER_TWEET_ENDPOINT = "/statuses/user_timeline.json";
    private final String GET_USERID_ENDPOINT = "/friends/ids.json";
    private final String MUTES_USERS_ENDPOINT = "/mutes/users/create.json";
    private final String UN_MUTES_USERS_ENDPOINT = "/mutes/users/destroy.json";
    private final String GET_BLOKED_USERS_ENDPOINT = "/blocks/ids.json";
    private final String FOLLOW_USERS_ENDPOINT = "/friendships/create.json";
    private final String UN_FOLLOW_USERS_ENDPOINT = "/friendships/destroy.json";
    private final String SEARCH_ENDPOINT = "/users/search.json";
    private final String RETWEET_ENDPOINT = "/retweet/:id.json";
    private final String CREATE_WELCOME_MESSAGE_ENDPOINT = "/direct_messages/welcome_messages/new.json";
    private final String UPDATE_WELCOME_MESSAGE_ENDPOINT = "/direct_messages/welcome_messages/update.json";
    private final String GET_AVAILABLE_TRENDS_ENDPOINT="/trends/available.json";
    private final String DELETE_BANNER_ENDPOINT = "/account/remove_profile_banner.json";
    public ValidatableResponse getUserIDD(String userIdNum) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("user_id", userIdNum)
                .when().get(this.baseUri + this.GET_USERID_ENDPOINT)
                .then();
    }
    public ValidatableResponse getUserTimelineTweets() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUri + this.GET_USER_TWEET_ENDPOINT)
                .then();
    }
    public ValidatableResponse createTweet(String tweet) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("status", tweet)
                .when().post(this.baseUri + this.CREATE_TWEET_ENDPOINT)
                .then();
    }
    public ValidatableResponse deleteTweet(Long tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id", tweetId)
                .when().post(this.baseUri + this.DELETE_TWEET_ENDPOINT)
                .then();
    }
    public ValidatableResponse muteUsers(String Id) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("id", Id)
                .when().post(this.baseUri + this.MUTES_USERS_ENDPOINT)
                .then();
    }
    public ValidatableResponse unMuteUsers(String Id) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id", Id)
                .when().post(this.baseUri + this.UN_MUTES_USERS_ENDPOINT)
                .then();
    }
    public ValidatableResponse getBlokedUsers() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUri + this.GET_BLOKED_USERS_ENDPOINT)
                .then();
    }
    public ValidatableResponse followUser(String id) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("id", id)
                .when().post(this.baseUri + this.FOLLOW_USERS_ENDPOINT)
                .then();
    }
    public ValidatableResponse unFollowUser(String id) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id", id)
                .when().post(this.baseUri + this.UN_FOLLOW_USERS_ENDPOINT)
                .then();
    }
    public ValidatableResponse searchProfile(String q) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("q", q)
                .when().get(this.baseUri + SEARCH_ENDPOINT)
                .then();
    }

    public ValidatableResponse retweetSuccessfully(String tweet) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("status", tweet)
                .when().post(this.baseUri + this.RETWEET_ENDPOINT)
                .then();
    }
    public ValidatableResponse createWelcomeMessage(String message) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("message", message)
                .when().post(this.baseUri + this.CREATE_WELCOME_MESSAGE_ENDPOINT)
                .then();
    }
    public ValidatableResponse updateWelcomeMessage(String userID,String message) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("hello", message)
                .when().put(this.baseUri + this.UPDATE_WELCOME_MESSAGE_ENDPOINT)
                .then();
    }
    public ValidatableResponse getAvailableTrends() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUri + this.GET_AVAILABLE_TRENDS_ENDPOINT)
                .then();
    }
    public ValidatableResponse deleteBanner() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().post(this.baseUri + this.DELETE_BANNER_ENDPOINT)
                .then();
    }
}