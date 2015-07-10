package org.grails.demo

import grails.rest.Resource

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlType

@Resource(uri = '/v1/customers')
class Customer implements Serializable {
    String firstName
    String lastName
    String username

    static constraints = {
        firstName nullable: false
        lastName nullable: false
        username unique: true
    }
}