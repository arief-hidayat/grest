package com.qultweet.data

import org.apache.commons.lang.builder.EqualsBuilder
import org.apache.commons.lang.builder.HashCodeBuilder

class PopularQultweet implements Serializable {

	Long convid
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
	Long convCount

	int hashCode() {
		def builder = new HashCodeBuilder()
		builder.append convid
		builder.append statusid
		builder.append userid
		builder.append screenName
		builder.append text
		builder.append createdAt
		builder.append retweetCount
		builder.append isFavorited
		builder.append isRetweet
		builder.append isTruncated
		builder.append convidCount
		builder.append convCount
		builder.toHashCode()
	}

	boolean equals(other) {
		if (other == null) return false
		def builder = new EqualsBuilder()
		builder.append convid, other.convid
		builder.append statusid, other.statusid
		builder.append userid, other.userid
		builder.append screenName, other.screenName
		builder.append text, other.text
		builder.append createdAt, other.createdAt
		builder.append retweetCount, other.retweetCount
		builder.append isFavorited, other.isFavorited
		builder.append isRetweet, other.isRetweet
		builder.append isTruncated, other.isTruncated
		builder.append convidCount, other.convidCount
		builder.append convCount, other.convCount
		builder.isEquals()
	}

	static mapping = {
//        table 'view'
        cache usage : "read-only"
        id composite: ["convid", "statusid", "userid", "screenName", "text", "createdAt", "retweetCount", "isFavorited", "isRetweet", "isTruncated", "convidCount", "convCount"]
		version false
	}

	static constraints = {
		statusid nullable: true
		userid nullable: true
		screenName nullable: true, maxSize: 100
		createdAt nullable: true, maxSize: 19
		isFavorited nullable: true
		isRetweet nullable: true
		isTruncated nullable: true
		convCount nullable: true
	}
}
