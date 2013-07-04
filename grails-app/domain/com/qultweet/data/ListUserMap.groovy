package com.qultweet.data

import org.apache.commons.lang.builder.EqualsBuilder
import org.apache.commons.lang.builder.HashCodeBuilder

class ListUserMap implements Serializable {

	Long listId
	Long userId

	int hashCode() {
		def builder = new HashCodeBuilder()
		builder.append listId
		builder.append userId
		builder.toHashCode()
	}

	boolean equals(other) {
		if (other == null) return false
		def builder = new EqualsBuilder()
		builder.append listId, other.listId
		builder.append userId, other.userId
		builder.isEquals()
	}

	static mapping = {
		id composite: ["listId", "userId"]
		version false
	}
}
