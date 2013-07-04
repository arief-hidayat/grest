package com.qultweet.data

class TweetMediaEntityController  extends NestedRestfulController {
    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    TweetMediaEntityController() {
        super(TweetMediaEntity, [new IdField("statusid", "publicTweetId")] as Set, // tweetId passed by nested url-mapping
            [new IdField("mediaid")] as Set) 
    }
}