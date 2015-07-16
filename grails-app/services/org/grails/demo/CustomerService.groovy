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
    Customer getCustomer(
            @WebParam(name = 'CustomerId') int customerId,
            @WebParam(name = 'FirstName') String firstName
    ) {
        Thread.sleep(5000)  //Simulate latency
        loadCustomer(customerId, firstName)
    }

    public Customer loadCustomer(int id, String firstName = null) {
        Customer customer = null
        try {
            if(firstName) {
                customer = Customer.findByIdAndFirstName(id, firstName) ?: new Customer(id: id)
            } else {
                customer = Customer.get(id) ?: new Customer(id: id)
            }
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
            @WebParam(name = 'CustomerId') int customerId,
            @WebParam(name = 'PaymentDate') Date paymentDate,
            @WebParam(name = 'PaymentAmount') Double paymentAmount
    ) {
        Customer customer = loadCustomer(customerId)
        Payment payment = new Payment(paymentDate: paymentDate, paymentAmount: paymentAmount).save()
        customer.addToPayments(payment).save()
        customer
    }
}
