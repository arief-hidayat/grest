package com.qultweet.data
import grails.rest.*;

/**
* for nested entity. 
* entity must have static retrieve(def params) method.
**/
class NestedRestfulController<T>  extends CustomRestfulController<T> {
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE", index : "GET", show: "GET"]
    final Set<IdField> parentIds, ownIds

    final boolean hasIdField

    NestedRestfulController(Class<T> resource, Set<IdField> parentIds, Set<IdField> ownIds) {
        super(resource)
        this.parentIds = parentIds ?: new HashSet<IdField>() // key is the field name declared in parent class, value is the field name in this domain class.
        this.ownIds = ownIds ?: new HashSet<IdField>()
        hasIdField = ownIds.contains("id")
    }
    @Override
    T queryForResource(def params) {
        resource.retrieve(queryParams)
    }

    @Override
    protected def getQueryParams() {
        getParametersToBind(); 
        def query = [:]
        for(IdField id : parentIds) if(params[id.name]) query.put(id.name, params[id.name])
        for(IdField id : ownIds) if(params[id.name]) query.put(id.name, params[id.name])
        query
    }
    private void populateParams(IdField id) {
        if(id.name != id.nameInParams) {
            if(params[id.nameInParams])  params.put(id.name, id.valueOf(params[id.nameInParams]))
            params.remove(id.nameInParams)
        }
    }

    @Override
    protected Map getParametersToBind() {
        for(IdField id : parentIds) {populateParams(id)}
        def idValue = params.id
        if(idValue && !hasIdField) {
            Set<String> fieldWithValue = new HashSet<String>()
            for(IdField id : ownIds) {
                if(params[id.nameInParams]) fieldWithValue.add(id.name)
                populateParams(id)
            }
            if(ownIds.size() != fieldWithValue.size() + 1) {
                //throw new Exception("This doesn't have all primary key.")
            } else {
                IdField currentIdField = (ownIds - fieldWithValue)[0]
                params.put(currentIdField.name, currentIdField.valueOf(idValue))
            }
            params.remove("id")
        }
        this.params
    }
}

class IdField implements Comparable<IdField> {
    String name, nameInParams;
    Class type;
    public IdField(String name, String nameInParams = null, Class type = Long.class) {
        this.name = name; this.nameInParams = nameInParams ?: name; this.type  = type ?: Long.class;
    }

    public int compareTo(IdField other) {
        this.name.compareTo(other.name)
    }

    public def valueOf(idValue) {
        if(idValue.class != this.type) {
            if(this.type == Long.class && idValue instanceof String) return Long.parseLong(idValue)
            if(this.type == Integer.class && idValue instanceof String) return Integer.parseInt(idValue)
            if(this.type == Boolean.class && idValue instanceof String) return Boolean.parseBoolean(idValue)
            if(this.type == String.class && idValue instanceof Number) return idValue.toString()
        }
        return idValue
    }
}
