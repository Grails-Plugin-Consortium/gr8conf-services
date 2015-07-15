package org.grails.demo

import org.grails.cxf.utils.GrailsCxfEndpoint

import javax.jws.WebMethod
import javax.jws.WebParam
import javax.jws.WebResult
import javax.xml.ws.ResponseWrapper

@GrailsCxfEndpoint(address = '/v1/customers')
class CustomerService {

    @WebMethod(operationName = "GetCustomers")
    @WebResult(name = "Customers")
    @ResponseWrapper(
            localName = "GetCustomersResponse",
            targetNamespace = "http://demo.grails.org",
            className = "org.grails.demo.customer.GetCustomersResponse"
    )
    List<Customer> getCustomers() {
        Thread.sleep(5000) //Simulate latency
        Customer.list()
    }

    @WebMethod(operationName = "GetCustomer")
    @WebResult(name = "Customer")
    @ResponseWrapper(
            localName = "GetCustomerResponse",
            targetNamespace = "http://demo.grails.org",
            className = "org.grails.demo.customer.GetCustomerResponse"
    )
    Customer getCustomer(@WebParam(name = 'customerId') int customerId) {
        Thread.sleep(5000)  //Simulate latency
        loadCustomer(customerId)
    }

    public Customer loadCustomer(int customerId) {
        Customer customer = null
        try {
            customer = Customer.read(customerId) ?: new Customer(id: customerId)
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception)
        }
        customer
    }

    @WebMethod(operationName = "MakePayment")
    @WebResult(name = "Customer")
    @ResponseWrapper(
            localName = "MakePaymentResponse",
            targetNamespace = "http://demo.grails.org",
            className = "org.grails.demo.customer.MakePaymentResponse"
    )
    Customer makePayment(
            @WebParam(name = 'customerId') int customerId,
            @WebParam(name = 'paymentDate') Date paymentDate,
            @WebParam(name = 'paymentAmount') Double paymentAmount
    ) {
        Customer customer = loadCustomer(customerId)
        Payment payment = new Payment(date: paymentDate, amount: paymentAmount).save()
        customer.addToPayments(payment).save()
        customer
    }
}
