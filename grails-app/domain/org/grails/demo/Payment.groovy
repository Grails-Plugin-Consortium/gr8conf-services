package org.grails.demo

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlElement


@XmlAccessorType(XmlAccessType.NONE)
class Payment {

    @XmlElement(name='PaymentDate')
    Date paymentDate
    @XmlElement(name='PaymentAmount')
    Double paymentAmount

    static constraints = {
        paymentDate()
        paymentAmount()
    }
}
