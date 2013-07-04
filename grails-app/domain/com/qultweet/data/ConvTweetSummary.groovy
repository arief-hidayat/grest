package com.qultweet.data

class ConvTweetSummary {

	Long statusid
	Long userid
	String screenName
	String text
	Date createdAt
	Integer retweetCount
	Boolean isFavorited
	Boolean isRetweet
	Boolean isTruncated
	Integer convidCount

	static mapping = {
		id column: "convid", generator: "assigned"
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
	}
}
