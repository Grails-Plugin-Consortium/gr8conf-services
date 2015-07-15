package org.grails.demo

import grails.rest.Resource

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlElementWrapper

@Resource(uri = '/v1/customers')
@XmlAccessorType(XmlAccessType.NONE)
class Customer implements Serializable {

    static hasMany = [payments: Payment]

    @XmlElement(name='ID')
    Long id
    @XmlElement(name='FirstName')
    String firstName
    @XmlElement(name='LastName')
    String lastName
    @XmlElement(name='Username')
    String username

    @XmlElementWrapper(name="Payments")
    @XmlElement(name="Payment")
    List<Payment> payments = []

    static constraints = {
        firstName nullable: false
        lastName nullable: false
        username unique: true
    }

    static mapping = {
        payments lazy: false
    }
}