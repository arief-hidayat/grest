class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.${format})?"{
            constraints {
                // apply constraints here
            }
        }
        "/tweets"(resources:"publicTweet") {
        	"/mediaLinks"(resources:"tweetMediaEntity")
        	"/urlLinks"(resources:"tweetUrlEntity")
        	"/hashtags"(resources:"tweetHashtagEntity")
        	"/mentions"(resources:"tweetUserMentionEntity")
        }

        "/"(view:"/index")
        "500"(view:'/error')
	}
}
