package com.knoldus;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;


class TwitterFeeds {

    static final Logger logger = LoggerFactory.getLogger(TwitterFeeds.class);
    Config conf = ConfigFactory.systemEnvironment();


    protected static List<Status> filterTweets(TwitterFeeds tweets, Comparator<Status> comparator) {
        try {
            logger.info("Checking Twitter Feed...");
            List<Status> list = tweets.getTweets();
            list.sort(comparator);
            return list;
        } catch (Exception ex) {
            logger.warn("Exception Occurred : " + ex);
            return Collections.emptyList();
        }
    }

    List<Status> getTweets() throws TwitterException {
        try {
            String consumerKey = conf.getString("consumerKey");
            String consumerSecretKey = conf.getString("consumerSecretKey");
            String accessToken = conf.getString("accessToken");
            String accessTokenSecret = conf.getString("accessTokenSecret");

            ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
            configurationBuilder.setDebugEnabled(true)
                    .setOAuthConsumerKey(consumerKey)
                    .setOAuthConsumerSecret(consumerSecretKey)
                    .setOAuthAccessToken(accessToken)
                    .setOAuthAccessTokenSecret(accessTokenSecret);

            Twitter twitter = new TwitterFactory(configurationBuilder.build()).getInstance();
            return twitter.getUserTimeline();
        } catch (Exception ex) {
            return Collections.emptyList();
        }
    }

    void prettyPrint(Status status) {
        if (status.isRetweet()) {
            System.out.println("Retweet: " + status.getText());
            System.out.println("Retweets: " + status.getRetweetCount() + "\tLikes: " + status.getFavoriteCount() + "\n");
        } else {
            System.out.println("Tweet: " + status.getText());
            System.out.println("Retweets: " + status.getRetweetCount() + "\tLikes: " + status.getFavoriteCount() + "\n");
        }
    }
}
