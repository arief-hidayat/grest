package com.qultweet.data
import grails.rest.*
@Resource
class PublicTweet {

	Long userid
	String screenName
	String text
	Long inReplyToStatusId
	String inReplyToScreenName
	Long inReplyToUserId
	Date createdAt
	Long inRetweetToStatusId
	Long inRetweetToUserId
	Integer retweetCount
	Boolean isFavorited
	Boolean isRetweet
	Boolean isTruncated
	String contributors
	Long convid
	Boolean isReplyPublic
	Boolean isUstadz

	static mapping = {
		id column: "statusid", generator: "assigned"
		version false
	}

	static constraints = {
		userid nullable: true
		screenName nullable: true, maxSize: 100
		text nullable: true
		inReplyToStatusId nullable: true
		inReplyToScreenName nullable: true, maxSize: 100
		inReplyToUserId nullable: true
		createdAt nullable: true, maxSize: 19
		inRetweetToStatusId nullable: true
		inRetweetToUserId nullable: true
		retweetCount nullable: true
		isFavorited nullable: true
		isRetweet nullable: true
		isTruncated nullable: true
		contributors nullable: true
		convid nullable: true
	}
}
