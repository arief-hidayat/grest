package com.qultweet.data

class TweetUrlEntityController extends NestedRestfulController {
    static responseFormats = ['json', 'xml']
    TweetUrlEntityController() {
        super(TweetUrlEntity, [new IdField("statusid", "publicTweetId")] as Set, // tweetId passed by nested url-mapping
            [new IdField("seqid")] as Set) 
    }
}