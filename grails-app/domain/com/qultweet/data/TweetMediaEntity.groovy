package com.qultweet.data

import org.apache.commons.lang.builder.EqualsBuilder
import org.apache.commons.lang.builder.HashCodeBuilder
import grails.rest.*
@Resource
class TweetMediaEntity implements Serializable {

	static TweetMediaEntity retrieve(def params) {
    	if(!params.statusid || !params.mediaid) return null;
    	findWhere(statusid : params.statusid, mediaid : params.mediaid)
		//find("from TweetMediaEntity e where e.statusId=:statusid and e.mediaid=:mediaid", params, [cache : true])
	}
	static List<TweetMediaEntity> listByStatus(Long statusId) {
		if(!statusId) return null; 
		findAllWhere(statusid : statusId) 
		//findAll("from TweetMediaEntity e where e.statusId=:statusId", [statusId : statusId], [cache : true])
	}
	Long statusid
	Long mediaid
	String mediaUrl
	String mediaUrlHttps
	String url
	String displayUrl
	String expandedUrl
	Integer sizeLargeW
	String sizeLargeResize
	Integer sizeLargeHeight
	Integer sizeMediumW
	String sizeMediumResize
	Integer sizeMediumH
	Integer sizeSmallW
	String sizeSmallResize
	Integer sizeSmallH
	Integer sizeThumbW
	String sizeThumbResize
	Integer sizeThumbH
	String type
	Integer indexStart
	Integer indexEnd
	Date createdAt

	int hashCode() {
		def builder = new HashCodeBuilder()
		builder.append statusid
		builder.append mediaid
		builder.toHashCode()
	}

	boolean equals(other) {
		if (other == null || !(other instanceof TweetMediaEntity)) return false
		def builder = new EqualsBuilder()
		builder.append statusid, other.statusid
		builder.append mediaid, other.mediaid
		builder.isEquals()
	}

	static mapping = {
		id composite: ["statusid", "mediaid"]
        sizeLargeW column : "size_large_w"
        sizeMediumW  column : "size_medium_w"
        sizeSmallW  column : "size_small_w"
        sizeThumbW  column : "size_thumb_w"
        sizeMediumH  column : "size_medium_h"
        sizeSmallH  column : "size_small_h"
        sizeThumbH  column : "size_thumb_h"
		version false
	}

	static constraints = {
		mediaUrl nullable: true, maxSize: 300
		mediaUrlHttps nullable: true, maxSize: 65535
		url nullable: true, maxSize: 65535
		displayUrl nullable: true, maxSize: 300
		expandedUrl nullable: true, maxSize: 65535
		sizeLargeW nullable: true
		sizeLargeResize nullable: true, maxSize: 45
		sizeLargeHeight nullable: true
		sizeMediumW nullable: true
		sizeMediumResize nullable: true, maxSize: 45
		sizeMediumH nullable: true
		sizeSmallW nullable: true
		sizeSmallResize nullable: true, maxSize: 45
		sizeSmallH nullable: true
		sizeThumbW nullable: true
		sizeThumbResize nullable: true, maxSize: 45
		sizeThumbH nullable: true
		type nullable: true, maxSize: 45
		indexStart nullable: true
		indexEnd nullable: true
		createdAt nullable: true, maxSize: 19
	}
}
