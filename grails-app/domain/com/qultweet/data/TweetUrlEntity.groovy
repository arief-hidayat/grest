package com.qultweet.data

import org.apache.commons.lang.builder.EqualsBuilder
import org.apache.commons.lang.builder.HashCodeBuilder
import grails.rest.*
@Resource
class TweetUrlEntity implements Serializable {

	static TweetUrlEntity retrieve(def params) {
    	if(!params.statusid || !params.seqid) return null;
    	findWhere(params)
		//find("from TweetUrlEntity e where e.statusId=:statusid and e.seqid=:seqid", params, [cache : true])
	}
	static List<TweetUrlEntity> listByStatus(Long statusId) {
		if(!statusId) return null; 
		findAllWhere(statusid : statusId) 
		//findAll("from TweetUrlEntity e where e.statusId=:statusId", [statusId : statusId], [cache : true])
	}
	Long statusid
	Integer seqid
	String url
	String displayUrl
	String expandedUrl
	Integer indexStart
	Integer indexEnd
	String longUrl
	String pageImg
	String pageTitle
	String pageDesc
	Boolean isUrlResolved
	String noembedHtml
	String noembedTitle
	String noembedThumbnailUrl
	Integer noembedWidth
	Integer noembedHeight
	String noembedUrl
	String noembedProviderName
	String fbThumbnailUrl
	String fbTitle
	String fbSiteName
	String fbPageDesc
	Date createdAt
	Boolean isSnapshotFailed

	int hashCode() {
		def builder = new HashCodeBuilder()
		builder.append statusid
		builder.append seqid
		builder.toHashCode()
	}

	boolean equals(other) {
		if (other == null || !(other instanceof TweetUrlEntity)) return false
		def builder = new EqualsBuilder()
		builder.append statusid, other.statusid
		builder.append seqid, other.seqid
		builder.isEquals()
	}

	static mapping = {
		id composite: ["statusid", "seqid"]
		version false
	}

	static constraints = {
		url nullable: true, maxSize: 300
		displayUrl nullable: true, maxSize: 65535
		expandedUrl nullable: true, maxSize: 65535
		indexStart nullable: true
		indexEnd nullable: true
		longUrl nullable: true, maxSize: 300
		pageImg nullable: true, maxSize: 65535
		pageTitle nullable: true, maxSize: 400
		pageDesc nullable: true, maxSize: 65535
		isUrlResolved nullable: true
		noembedHtml nullable: true, maxSize: 65535
		noembedTitle nullable: true, maxSize: 400
		noembedThumbnailUrl nullable: true, maxSize: 300
		noembedWidth nullable: true
		noembedHeight nullable: true
		noembedUrl nullable: true, maxSize: 300
		noembedProviderName nullable: true, maxSize: 45
		fbThumbnailUrl nullable: true, maxSize: 300
		fbTitle nullable: true, maxSize: 400
		fbSiteName nullable: true, maxSize: 400
		fbPageDesc nullable: true, maxSize: 65535
		createdAt nullable: true, maxSize: 19
	}
}
