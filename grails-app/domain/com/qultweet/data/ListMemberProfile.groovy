package com.qultweet.data

class ListMemberProfile {

	String screenName
	String name
	Date createdAt
	String description
	String location
	String profileImgUrl
	String profileImgUrlHttps
	String language
	Integer followerCount
	Integer friendCount
	Integer favoriteCount
	String url
	Boolean isProtected
	Boolean isTranslator
	Boolean isVerified
	Boolean isGeoEnabled
	String timezone

	static mapping = {
		id column: "userid", generator: "assigned"
		version false
	}

	static constraints = {
		screenName nullable: true, maxSize: 100
		name nullable: true, maxSize: 100
		createdAt nullable: true, maxSize: 19
		description nullable: true, maxSize: 300
		location nullable: true, maxSize: 100
		profileImgUrl nullable: true, maxSize: 65535
		profileImgUrlHttps nullable: true, maxSize: 65535
		language nullable: true, maxSize: 100
		followerCount nullable: true
		friendCount nullable: true
		favoriteCount nullable: true
		url nullable: true, maxSize: 65535
		isProtected nullable: true
		isTranslator nullable: true
		isVerified nullable: true
		isGeoEnabled nullable: true
		timezone nullable: true, maxSize: 45
	}
}
