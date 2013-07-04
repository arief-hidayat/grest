package com.qultweet.data

class UstadzList {

	String screenName
	Boolean isControversial

	static mapping = {
		id column: "user_id", generator: "assigned"
		version false
	}

	static constraints = {
		screenName nullable: true, maxSize: 100, unique: true
	}
}
