package com.qultweet.data

class VisitorTracker {

	String pageVisited
	Date visitingTime
	String ipAddr

	static mapping = {
		id column: "visit_id"
		version false
	}

	static constraints = {
		pageVisited nullable: true, maxSize: 100
		visitingTime nullable: true, maxSize: 19
		ipAddr nullable: true, maxSize: 45
	}
}
