/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package grails.rest

import grails.artefact.Artefact
import grails.transaction.Transactional
import grails.util.GrailsNameUtils
import static org.springframework.http.HttpStatus.*

/**
 * Base class that can be extended to get the basic CRUD operations needed for a RESTful API
 *
 * @author Graeme Rocher
 * @since 2.3
 */
@Artefact("Controller")
@Transactional(readOnly = true)
class CustomRestfulController<T> {
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    Class resource
    String resourceName
    String resourceClassName

    CustomRestfulController(Class<T> resource) {
        this.resource = resource
        this.resourceClassName = resource.simpleName
        this.resourceName = GrailsNameUtils.getPropertyName(resource)
    }

    /**
     * Lists all resources up to the given maximum
     *
     * @param max The maximum
     * @return A list of resources
     */
    def index(Integer max) {
        def paginationParams  = [max : Math.min(max ?: 10, 100)]
        if(params.offset) paginationParams.offset = Integer.parseInt("${params.offset}")
        def model = [:]
        model.put("${resourceName}".toString(), resource.count())
        def list = queryForResourceList(queryParams, paginationParams)        
        respond list, model:model
    }

    /**
     * Shows a single resource
     * @param id The id of the resource
     * @return The rendered resource or a 404 if it doesn't exist
     */
    def show() {
        respond queryForResource(queryParams)
    }

    /**
     * Displays a form to create a new resource
     */
    def create() {
        respond createResource(getParametersToBind())
    }


    /**
     * Saves a resource
     */
    @Transactional
    def save() {
        def instance = createResource(getParametersToBind())
        instance.validate()
        if(instance.hasErrors()) {
            respond instance.errors, view:'create' // STATUS CODE 422
        }
        else {
            instance.save flush:true
            withFormat {
                html {
                    flash.message = message(code: 'default.created.message', args: [message(code: "${resourceName}.label".toString(), default: resourceClassName), instance.id])
                    redirect instance
                }
                '*' { render status: CREATED }
            }
        }
    }

    def edit() {
        respond queryForResource(queryParams)
    }

    /**
     * Updates a resource for the given id
     * @param id
     */
    @Transactional
    def update() {
        T instance = queryForResource(queryParams)
        if(instance == null) {
            render status:404
            return
        }
        else {
            instance.properties = this.params
        }
        if(instance.hasErrors()) {
            respond instance.errors, view:'edit' // STATUS CODE 422
        }
        else {
            instance.save flush:true
            withFormat {
                html {
                    flash.message = message(code: 'default.updated.message', args: [message(code: "${resourceClassName}.label".toString(), default: resourceClassName), instance.id])
                    redirect instance
                }
                '*'{ render status: OK }
            }
        }
    }

    protected def getQueryParams() {
        this.params
    }



    /**
     * Deletes a resource for the given id
     * @param id The id
     */
    @Transactional
    def delete() {
        def instance = queryForResource(queryParams)
        if(instance) {
            instance.delete flush:true
            withFormat {
                html {
                    flash.message = message(code: 'default.deleted.message', args: [message(code: "${resourceClassName}.label".toString(), default: resourceClassName), instance.id])
                    redirect action:"index", method:"GET"
                }
                '*'{ render status: NO_CONTENT } // NO CONTENT STATUS CODE
            }
        }
        else {
            render status: NOT_FOUND
        }
    }

    /**
     * The parameters that can be bound to a domain instance. Defaults to all, subclasses should override and customize the behavior
     *
     * @return The parameters
     */
    protected Map getParametersToBind() {
        this.params
    }

    /**
     * Queries for a resource for the given id
     *
     * @param id The id
     * @return The resource or null if it doesn't exist
     */
    protected T queryForResource(def params) {
        resource.get(params.id)
    }

    protected List<T> queryForResourceList(def qParams, def paginationParams) {
        resource.createCriteria().list(paginationParams){
            for(String key : qParams.keySet()) eq(key, qParams[key])
        }
    }

    /**
     * Creates a new instance of the resource for the given parameters
     *
     * @param params The parameters
     * @return The resource instance
     */
    protected T createResource(Map params) {
        resource.newInstance(params)
    }


}