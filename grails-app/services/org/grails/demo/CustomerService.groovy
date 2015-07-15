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
    Customer getCustomer(@WebParam(name = 'ID') int id) {
        Thread.sleep(5000)  //Simulate latency
        loadCustomer(id)
    }

    public Customer loadCustomer(int id) {
        Customer customer = null
        try {
            customer = Customer.read(id) ?: new Customer(id: id)
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
            @WebParam(name = 'ID') int customerId,
            @WebParam(name = 'PaymentDate') Date paymentDate,
            @WebParam(name = 'PaymentAmount') Double paymentAmount
    ) {
        Customer customer = loadCustomer(customerId)
        Payment payment = new Payment(date: paymentDate, amount: paymentAmount).save()
        customer.addToPayments(payment).save()
        customer
    }
}
