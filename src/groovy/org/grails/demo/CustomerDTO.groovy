package org.grails.demo

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlType

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
        name = "Customer",
        propOrder = ["id", "firstName", "lastName", "username"]
)
class CustomerDTO implements Serializable {
    Long id
    String firstName
    String lastName
    String username
}
