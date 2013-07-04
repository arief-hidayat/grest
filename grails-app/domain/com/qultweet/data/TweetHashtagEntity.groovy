package com.qultweet.data

import org.apache.commons.lang.builder.EqualsBuilder
import org.apache.commons.lang.builder.HashCodeBuilder
import grails.rest.*
@Resource
class TweetHashtagEntity implements Serializable {

	static TweetHashtagEntity retrieve(def params) {
    	if(!params.statusid || !params.userid || ! params.hashtagText) return null;
    	findWhere(statusid : params.statusid, userid : params.userid, hashtagText: params.hashtagText)
		//find("from TweetHashtagEntity e where e.statusId=:statusid and e.userid=:userid and e.hashtagText=:hashtagText", params, [cache : true])
	}
	static List<TweetHashtagEntity> listByStatus(Long statusId) {
		if(!statusId) return null;
		findAllWhere(statusid : statusId) 
		//findAll("from TweetHashtagEntity e where e.statusId=:statusId", [statusId : statusId], [cache : true])
	}

	Long statusid
	String hashtagText
	Long userid
	Date createdAt
	Integer indexStart
	Integer indexEnd

	int hashCode() {
		def builder = new HashCodeBuilder()
		builder.append statusid
		builder.append hashtagText
		builder.append userid
		builder.toHashCode()
	}

	boolean equals(other) {
		if (other == null || !(other instanceof TweetHashtagEntity)) return false
		def builder = new EqualsBuilder()
		builder.append statusid, other.statusid
		builder.append hashtagText, other.hashtagText
		builder.append userid, other.userid
		builder.isEquals()
	}

	static mapping = {
		id composite: ["statusid", "hashtagText", "userid"]
		version false
	}

	static constraints = {
		hashtagText maxSize: 100
		createdAt nullable: true, maxSize: 19
		indexStart nullable: true
		indexEnd nullable: true
	}
}
