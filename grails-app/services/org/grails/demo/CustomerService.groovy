package org.grails.demo

import org.grails.cxf.utils.GrailsCxfEndpoint

import javax.jws.WebMethod
import javax.jws.WebParam
import javax.jws.WebResult

@GrailsCxfEndpoint(address = '/v1/customers')
class CustomerService {

    @WebMethod(operationName = "getCustomers")
    @WebResult(name = "customer")
    List<CustomerDTO> getCustomer() {
        Customer.list().collect { new CustomerDTO(firstName: it.firstName, lastName: it.lastName, username: it.username, id: it.id) }
    }

    @WebMethod(operationName = "getCustomer")
    @WebResult(name = "customer")
    CustomerDTO getCustomer(@WebParam(name = 'customerId') int customerId) {
        def customer = Customer.load(customerId).properties
        new CustomerDTO(firstName: customer?.firstName, lastName: customer?.lastName, username: customer?.username, id: customer?.id)
    }
}
