package com.qultweet.data

class TopTweet {

	Long statusid
	Long userid
	String screenName
	String text
	Date createdAt
	Integer retweetCount
	Boolean isFavorited
	Boolean isRetweet
	Boolean isTruncated
	Integer convCount
	Double score
	Integer seqnum

	static mapping = {
		id column: "convid"
		version false
	}

	static constraints = {
		statusid nullable: true, unique: true
		userid nullable: true
		screenName nullable: true, maxSize: 100
		createdAt nullable: true, maxSize: 19
		isFavorited nullable: true
		isRetweet nullable: true
		isTruncated nullable: true
		seqnum unique: true
	}
}
