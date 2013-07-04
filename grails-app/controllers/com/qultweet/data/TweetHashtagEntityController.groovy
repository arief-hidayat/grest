package com.qultweet.data

class TweetHashtagEntityController extends NestedRestfulController {
    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    TweetHashtagEntityController() {
        super(TweetHashtagEntity, [new IdField("statusid", "publicTweetId")] as Set, // tweetId passed by nested url-mapping
            [new IdField("userid"),new IdField("hashtagText", "hashtag", String.class)]  as Set) 
            // note that it passes 'hashtag' parameters instead of 'hashtagNext'
    }
}