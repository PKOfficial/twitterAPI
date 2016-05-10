package com.knoldus;

import twitter4j.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;

public class Tweets extends TwitterFeeds {

    static final Logger logger = LoggerFactory.getLogger(Tweets.class);

    public static void main(String[] args) {

        TwitterFeeds tweet = new TwitterFeeds();
        Comparator<Status> sortByDate = (Status one, Status two) -> one.getCreatedAt().compareTo(two.getCreatedAt());
        Comparator<Status> sortByReTweetCount = (Status one, Status two) -> ((Integer) one.getRetweetCount()).compareTo((Integer) two.getRetweetCount());
        Comparator<Status> sortByLikesCount = (Status one, Status two) -> ((Integer) one.getFavoriteCount()).compareTo((Integer) two.getFavoriteCount());

        logger.info("============ Sort By Date ============");
        filterTweets(tweet, sortByDate).stream().forEach(tweet::prettyPrint);

        logger.info("============ Sort By ReTweet Counts ============");
        filterTweets(tweet, sortByReTweetCount).stream().forEach(tweet::prettyPrint);

        logger.info("============ Sort By Likes ============");
        filterTweets(tweet, sortByLikesCount).stream().forEach(tweet::prettyPrint);
    }
}
