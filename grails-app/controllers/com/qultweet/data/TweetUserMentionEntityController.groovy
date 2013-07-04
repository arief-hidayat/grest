package com.qultweet.data

class TweetUserMentionEntityController extends NestedRestfulController {
    static responseFormats = ['json', 'xml']
    TweetUserMentionEntityController() {
        super(TweetUserMentionEntity, [new IdField("statusid", "publicTweetId")], // tweetId passed by nested url-mapping
            [new IdField("mentionedUserId"),new IdField("mentionedBy", "by")]) 
            // note that it passes 'mentionedBy' parameters instead of 'by'
    }
}
