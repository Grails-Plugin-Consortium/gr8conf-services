import org.grails.demo.Customer
import org.grails.demo.Payment

class BootStrap {

    def init = { servletContext ->

        Customer.findOrSaveWhere(firstName: 'Duncan', lastName: 'MacLeod', username: 'duncan@gmail.com').addToPayments(new Payment(date: new Date(), amount: 9.99d)).save()
        Customer.findOrSaveWhere(firstName: 'Connor', lastName: 'MacLeod', username: 'connor@gmail.com').addToPayments(new Payment(date: new Date(), amount: 10.99d)).save()
        Customer.findOrSaveWhere(firstName: 'Juan', lastName: 'Rameriz', username: 'juan@gmail.com').addToPayments(new Payment(date: new Date(), amount: 11.99d)).save()
        Customer.findOrSaveWhere(firstName: 'Victor', lastName: 'Kruger', username: 'victor@gmail.com').addToPayments(new Payment(date: new Date(), amount: 12.99d)).save()
        Customer.findOrSaveWhere(firstName: 'Richie', lastName: 'Ryan', username: 'richie@gmail.com').addToPayments(new Payment(date: new Date(), amount: 13.99d)).save()
        Customer.findOrSaveWhere(firstName: 'Joe', lastName: 'Dawson', username: 'joe@gmail.com').addToPayments(new Payment(date: new Date(), amount: 14.99d)).save()
        Customer.findOrSaveWhere(firstName: 'Adam', lastName: 'Pierson', username: 'methos@gmail.com').addToPayments(new Payment(date: new Date(), amount: 15.99d)).save()

    }
    def destroy = {
    }
}
