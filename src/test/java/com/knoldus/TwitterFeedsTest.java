package com.knoldus;

import org.junit.Test;
import twitter4j.Status;

import java.util.Comparator;
import java.util.List;

import static junit.framework.Assert.assertTrue;

public class TwitterFeedsTest extends TwitterFeeds{

    TwitterFeeds twitterFeed = new TwitterFeeds();

    @Test
    public void twitterFeedsSortByDate() {
        Comparator<Status> sortByDate = (Status one, Status two) -> one.getCreatedAt().compareTo(two.getCreatedAt());
        List<Status> list = filterTweets(twitterFeed, sortByDate);
        assertTrue(!list.isEmpty());
    }

    @Test
    public void twitterFeedsSortByReTweetCount() {
        Comparator<Status> sortByReTweetCount = (Status one, Status two) -> ((Integer) one.getRetweetCount()).compareTo((Integer) two.getRetweetCount());
        List<Status> list = filterTweets(twitterFeed, sortByReTweetCount);
        assertTrue(!list.isEmpty());
    }

    @Test
    public void twitterFeedsSortByLikesCount() {
        Comparator<Status> sortByLikesCount = (Status one, Status two) -> ((Integer) one.getFavoriteCount()).compareTo((Integer) two.getFavoriteCount());
        List<Status> list = filterTweets(twitterFeed, sortByLikesCount);
        assertTrue(!list.isEmpty());
    }

}
