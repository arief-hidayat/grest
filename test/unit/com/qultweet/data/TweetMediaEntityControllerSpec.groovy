package com.qultweet.data

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(TweetMediaEntityController)
class TweetMediaEntityControllerSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
    	given:
    	params.statusid = 1
    	params.mediaid = 1
    	params.mediaUrl = "http://facebook.com"
    	when:
    	controller.create()
    	then:
    	TweetMediaEntity.count() == 1
    }
}