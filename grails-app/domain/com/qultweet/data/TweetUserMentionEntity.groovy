package com.qultweet.data

import org.apache.commons.lang.builder.EqualsBuilder
import org.apache.commons.lang.builder.HashCodeBuilder
import grails.rest.*
@Resource
class TweetUserMentionEntity implements Serializable {

	static TweetUserMentionEntity retrieve(def params) {
    	if(!params.statusid || !params.mentionedUserId || ! params.mentionedBy) return null;
    	findWhere(statusid : params.statusid, mentionedUserId : params.mentionedUserId, mentionedBy : params.mentionedBy)
		//find("from TweetUserMentionEntity e where e.statusId=:statusid and e.mentionedUserId=:mentionedUserId and e.mentionedBy=:mentionedBy", params, [cache : true])
	}
	static List<TweetUserMentionEntity> listByStatus(Long statusId) {
		if(!statusId) return null; 
		findAllWhere(statusid : statusId) 
		//findAll("from TweetUserMentionEntity e where e.statusId=:statusId", [statusId : statusId], [cache : true])
	}

	Long statusid
	Long mentionedUserId
	Long mentionedBy
	String mentionedScreenName
	String mentionedName
	Integer indexStart
	Integer indexEnd
	Date createdAt
	Boolean isUstadz

	int hashCode() {
		def builder = new HashCodeBuilder()
		builder.append statusid
		builder.append mentionedUserId
		builder.append mentionedBy
		builder.toHashCode()
	}

	boolean equals(other) {
		if (other == null || !(other instanceof TweetUserMentionEntity)) return false
		def builder = new EqualsBuilder()
		builder.append statusid, other.statusid
		builder.append mentionedUserId, other.mentionedUserId
		builder.append mentionedBy, other.mentionedBy
		builder.isEquals()
	}

	static mapping = {
		id composite: ["statusid", "mentionedUserId", "mentionedBy"]
		version false
	}

	static constraints = {
		mentionedScreenName nullable: true, maxSize: 100
		mentionedName nullable: true, maxSize: 100
		indexStart nullable: true
		indexEnd nullable: true
		createdAt maxSize: 19
	}
}
