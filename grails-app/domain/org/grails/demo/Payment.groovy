package org.grails.demo

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlElement


@XmlAccessorType(XmlAccessType.NONE)
class Payment {

    @XmlElement(name='Date')
    Date date
    @XmlElement(name='Amount')
    Double amount

    static constraints = {
        date()
        amount()
    }
}
