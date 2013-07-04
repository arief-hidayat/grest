package com.qultweet.data

import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
@Mock([TweetMediaEntity])
class TweetMediaEntitySpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
    	given:
    	TweetMediaEntity entity = new TweetMediaEntity(statusid : 1, mediaid : 1, mediaUrl : 'http://facebook.com')
    	when:
    	entity.save()
    	then:
    	TweetMediaEntity.count() == 1
    }
}