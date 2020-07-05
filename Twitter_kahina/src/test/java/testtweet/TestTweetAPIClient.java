package testtweet;



import io.restassured.response.ResponseBody;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tweet.TweetAPIClient;
import java.util.UUID;

public class TestTweetAPIClient {
    private TweetAPIClient tweetAPIClient;

    @BeforeClass
    public void setUpTweetAPI() {
        this.tweetAPIClient = new TweetAPIClient();
    }


    @Test(enabled = false, priority = 1)
    public void testGetUserTimelineTweets() {
        ValidatableResponse response = this.tweetAPIClient.getUserTimelineTweets();
        ResponseBody body = (ResponseBody) response.extract().body();
        int actualResponseCode = response.extract().statusCode();
        int expectedResponseCode = 200;
        Assert.assertEquals(actualResponseCode, expectedResponseCode);
    }
    @Test(enabled = false, priority = 2)
    public void testGetgetUserIDD() {
        ValidatableResponse response = this.tweetAPIClient.getUserIDD("66780054");
        ResponseBody body = (ResponseBody) response.extract().body();
        int actualResponseCode = response.extract().statusCode();
        int expectedResponseCode = 200;
        Assert.assertEquals(actualResponseCode, expectedResponseCode);
    }
    @Test(enabled = false, priority = 3)
    public void testUserCanTweetSuccessfully() {
        String tweet = "Kahina Tweet" + UUID.randomUUID().toString();
        ValidatableResponse response = this.tweetAPIClient.createTweet(tweet);
        response.statusCode(200);
        String actualTweet = response.extract().body().path("text");
        Assert.assertEquals(tweet, actualTweet);
    }
    @Test(enabled = false, priority = 4)
    public void testUserCanNotTweetTheSameTweetTwiceInARow() {
        String tweet = "Kahina tweet " + UUID.randomUUID().toString();
        ValidatableResponse response = this.tweetAPIClient.createTweet(tweet);
        response.statusCode(200);
        String actualTweet = response.extract().body().path("text");
        Assert.assertEquals(tweet, actualTweet);
        response = this.tweetAPIClient.createTweet(tweet);
        response.statusCode(403);
        String expectedMessage = "Status is a duplicate.";
        String actualMessage = response.extract().body().path("errors[0].message");
        Assert.assertEquals(actualMessage, expectedMessage);
    }
    @Test(enabled = false, priority = 5)
    public void testDeleteTweet() {
        ValidatableResponse response = this.tweetAPIClient.deleteTweet(1279163025149300737l);
    }
    @Test(enabled = false, priority = 6)
    public void testMutesUsers() {
        ValidatableResponse response = this.tweetAPIClient.muteUsers("12345");
        ResponseBody body = (ResponseBody) response.extract().body();
        System.out.println("Response Body is: " + body.prettyPrint());
        int actualResponseCode = response.extract().statusCode();
        int expectedResponseCode = 200;
        Assert.assertEquals(actualResponseCode, expectedResponseCode);
    }
    @Test(enabled =false, priority = 7)
    public void testUnMutesUsers() {
        ValidatableResponse response = this.tweetAPIClient.unMuteUsers("12345");
        ResponseBody body = (ResponseBody) response.extract().body();
        System.out.println("Response Body is: " + body.prettyPrint());
        int actualResponseCode = response.extract().statusCode();
        int expectedResponseCode = 200;
        Assert.assertEquals(actualResponseCode, expectedResponseCode);
    }
    @Test(enabled = false, priority = 8)
    public void testGetBlockedUsers() {
        ValidatableResponse response = this.tweetAPIClient.getBlokedUsers();
        ResponseBody body = (ResponseBody) response.extract().body();
        System.out.println("Response Body is: " + body.prettyPrint());
        int actualResponseCode = response.extract().statusCode();
        int expectedResponseCode = 200;
        Assert.assertEquals(actualResponseCode, expectedResponseCode);
    }
    @Test(enabled = false, priority = 9)
    public void testFollowUser() {
        ValidatableResponse response = this.tweetAPIClient.followUser("2244994945");
        ResponseBody body = (ResponseBody) response.extract().body();
        System.out.println("Response Body is: " + body.prettyPrint());
        int actualResponseCode = response.extract().statusCode();
        int expectedResponseCode = 200;
        Assert.assertEquals(actualResponseCode, expectedResponseCode);
    }
    @Test(enabled = false, priority = 10)
    public void testUnFollowUser() {
        ValidatableResponse response = this.tweetAPIClient.unFollowUser("2244994945");
        ResponseBody body = (ResponseBody) response.extract().body();
        System.out.println("Response Body is: " + body.prettyPrint());
        int actualResponseCode = response.extract().statusCode();
        int expectedResponseCode = 200;
        Assert.assertEquals(actualResponseCode, expectedResponseCode);
    }
    @Test(enabled = false, priority = 11)
    public void testUserCanSearchProfileSuccessfully() {
        ValidatableResponse response = tweetAPIClient.searchProfile("massissilia");
       ResponseBody body = (ResponseBody) response.extract().body();
        System.out.println("Response Body is: " + body.prettyPrint());
        int actualResponseCode = response.extract().statusCode();
        int expectedResponseCode = 200;
        Assert.assertEquals(actualResponseCode,expectedResponseCode);
    }
    @Test(enabled = false, priority = 12)
    public void testUserCanRetweetSuccessfully() {
        String tweet = "Tweet again" + UUID.randomUUID().toString();
        ValidatableResponse response = this.tweetAPIClient.retweetSuccessfully(tweet);
        response.statusCode(200);
        String actualTweet = response.extract().body().path("text");
        Assert.assertEquals(tweet, actualTweet);
    }
    //use post method
    @Test(enabled = false,priority = 13)
    public void testUnsuccessfullyCreateWelcomeMessage() {
        ValidatableResponse response = this.tweetAPIClient.createWelcomeMessage("Hello Friends");
        ResponseBody body = (ResponseBody) response.extract().body();
        System.out.println("Response Body is: " + body.prettyPrint());
        int actualResponseCode = response.extract().statusCode();
        int expectedResponseCode = 403;
        Assert.assertEquals(actualResponseCode, expectedResponseCode);
    }
    // use put method
    @Test(enabled = false,priority = 14)
    public void testUnsuccessfullyUpdateWelcomeMessage() {
        ValidatableResponse response = this.tweetAPIClient.updateWelcomeMessage(" 2791751393","Hello");
        ResponseBody body = (ResponseBody) response.extract().body();
        System.out.println("Response Body is: " + body.prettyPrint());
        int actualResponseCode = response.extract().statusCode();
        int expectedResponseCode = 403;
        Assert.assertEquals(actualResponseCode, expectedResponseCode);
    }
    //use Get methods
    @Test(enabled = false, priority = 15)
    public void testGetAvailableTrends() {
        ValidatableResponse response = tweetAPIClient.getAvailableTrends();
        ResponseBody body = (ResponseBody) response.extract().body();
        System.out.println("Response Body is: " + body.prettyPrint());
        int actualResponseCode = response.extract().statusCode();
        int expectedResponseCode = 200;
        Assert.assertEquals(actualResponseCode,expectedResponseCode);
    }
    @Test(enabled = true,priority = 16)
    public void testProfileBanner() {
        ValidatableResponse response = this.tweetAPIClient.deleteBanner();
        ResponseBody body = (ResponseBody) response.extract().body();
        System.out.println("Response Body is: " + body.prettyPrint());
        int actualResponseCode = response.extract().statusCode();
        int expectedResponseCode = 200;
        Assert.assertEquals(actualResponseCode, expectedResponseCode);
    }
















}
