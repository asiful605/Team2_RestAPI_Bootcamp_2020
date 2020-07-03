package testtweet;

import base.TwitterAPIClient;
import com.github.scribejava.core.model.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tweet.TweetAPIClient;

import java.util.UUID;

public class TestTweetAPIClient {
    private TweetAPIClient tweetAPIClient;

    @BeforeClass
    public void setUpTweetAPI(){
        this.tweetAPIClient=new TweetAPIClient();
    }

    @Test(enabled = true)
    public void testUserCanTweetSuccessfully(){
        // 1. User send a tweet
        String tweet="We can tweet All Day "+ UUID.randomUUID().toString();
        ValidatableResponse response=this.tweetAPIClient.createTweet(tweet);
        // 2. Verify that the tweet was successfully send
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }
    protected TwitterAPIClient tweetsClient;
    @BeforeClass
    public  void setUp(){
        this.tweetsClient = new TwitterAPIClient();
    }
    @Test
    public void testUserCanTweetSuccessfully(){
        Response respone = tweetsClient.sentTweet("Tweeting from Queen,newyork");
        respone.getBody().prettyPrint();
        int statusCode = respone.getStatusCode();
        String statusLine=respone.getStatusLine();
        Assert.assertEquals(statusCode,200);
        Assert.assertEquals(statusLine,"HTTP/1.1 200 OK");

    }
    @Test
    public void testUserCannnotTweetDuplicateTweet(){
        Response respone = tweetsClient.sentTweet("Hey!This is Abidur" + UUID.randomUUID());
        respone.getBody().prettyPrint();
        int statusCode = respone.getStatusCode();
        String statusLine=respone.getStatusLine();
        Assert.assertEquals(statusCode,200);
        Assert.assertEquals(statusLine,"HTTP/1.1 200 OK");
    }
    @Test
    public void testUserCanSearchProfileSuccessfully(){
        Response response= tweetsClient.searchProfile("tsbristy111");
        response.getBody().prettyPrint();
        int statusCode= response.getStatusCode();
        String statusLine=response.getStatusLine();
        Assert.assertEquals(statusCode,200);
        Assert.assertEquals(statusLine,"HTTP/1.1 200 OK");
    }
    @Test
    public void testUser2CanSearchProfileSuccessfully(){
        Response response= tweetsClient.searchProfile("asif_");
        response.getBody().prettyPrint();
        int statusCode= response.getStatusCode();
        String statusLine=response.getStatusLine();
        Assert.assertEquals(statusCode,200);
        Assert.assertEquals(statusLine,"HTTP/1.1 200 OK");
    }
}
