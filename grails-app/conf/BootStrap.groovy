import com.qultweet.data.*

class BootStrap {

    def init = { servletContext ->
    	PublicTweet.list().each { it.delete() }
    	TweetMediaEntity.list().each { it.delete() }

    	PublicTweet p1 = new PublicTweet(text : "first tweet", isReplyPublic : true, isUstadz : true)
    	p1.id = 1
    	p1.save(flush : true, failOnError : true)

    	PublicTweet p2 = new PublicTweet(text : "second tweet", isReplyPublic : true, isUstadz : true)
    	p2.id = 2
    	p2.save(flush : true, failOnError : true)
    	
    	new TweetMediaEntity(statusid : 1, mediaid : 1, mediaUrl: "http://flickr.com/1").save(flush : true, failOnError : true)
    	new TweetMediaEntity(statusid : 1, mediaid : 2, mediaUrl: "http://flickr.com/2").save(flush : true, failOnError : true)
    	new TweetMediaEntity(statusid : 2, mediaid : 3, mediaUrl: "http://flickr.com/3").save(flush : true, failOnError : true)
		PublicTweet.list().each { 
			println "${it.text}. media count>> ${TweetMediaEntity.listByStatus(it.id).size()}" 
		 }
    	
    }
    def destroy = {
    }
}
