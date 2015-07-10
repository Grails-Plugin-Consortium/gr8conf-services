import org.grails.demo.Customer

class BootStrap {

    def init = { servletContext ->

        Customer.findOrSaveWhere(firstName: 'Duncan', lastName: 'MacLeod', username: 'duncan@gmail.com')
        Customer.findOrSaveWhere(firstName: 'Connor', lastName: 'MacLeod', username: 'connor@gmail.com')
        Customer.findOrSaveWhere(firstName: 'Juan', lastName: 'Rameriz', username: 'juan@gmail.com')
        Customer.findOrSaveWhere(firstName: 'Victor', lastName: 'Kruger', username: 'victor@gmail.com')
        Customer.findOrSaveWhere(firstName: 'Richie', lastName: 'Ryan', username: 'richie@gmail.com')
        Customer.findOrSaveWhere(firstName: 'Joe', lastName: 'Dawson', username: 'joe@gmail.com')
        Customer.findOrSaveWhere(firstName: 'Adam', lastName: 'Pierson', username: 'methos@gmail.com')

    }
    def destroy = {
    }
}
