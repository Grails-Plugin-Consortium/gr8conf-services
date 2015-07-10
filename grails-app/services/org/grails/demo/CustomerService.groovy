package org.grails.demo

import org.grails.cxf.utils.GrailsCxfEndpoint

import javax.jws.WebMethod
import javax.jws.WebParam
import javax.jws.WebResult
import javax.xml.ws.ResponseWrapper

@GrailsCxfEndpoint(address = '/v1/customers')
class CustomerService {

    @WebMethod(operationName = "getCustomers")
    @WebResult(name = "customer")
    @ResponseWrapper(
            localName = "GetCustomersResponse",
            targetNamespace = "http://demo.grails.org",
            className = "org.grails.demo.customer.GetCustomersResponse"
    )
    List<CustomerDTO> getCustomer() {
        Thread.sleep(5000) //Simulate latency
        Customer.list().collect { new CustomerDTO(firstName: it.firstName, lastName: it.lastName, username: it.username, id: it.id) }
    }

    @WebMethod(operationName = "getCustomer")
    @WebResult(name = "customer")
    @ResponseWrapper(
            localName = "GetCustomerResponse",
            targetNamespace = "http://demo.grails.org",
            className = "org.grails.demo.customer.GetCustomerResponse"
    )
    CustomerDTO getCustomer(@WebParam(name = 'customerId') int customerId) {
        Thread.sleep(5000)  //Simulate latency
        def customer = Customer.load(customerId).properties
        new CustomerDTO(firstName: customer?.firstName, lastName: customer?.lastName, username: customer?.username, id: customer?.id)
    }
}
