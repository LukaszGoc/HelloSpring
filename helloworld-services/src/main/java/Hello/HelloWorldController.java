package Hello;


import Hello.Data.Customers;
import Hello.Model.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class HelloWorldController {

    private static Customers customers = Customers.getInstance();

    static {
        customers.addCustomer(new Customer(1L, "John", "Monday"));
        customers.addCustomer(new Customer(2L, "Johny", "Tuesday"));
        customers.addCustomer(new Customer(3L, "Johnathan", "Wednesday"));
        customers.addCustomer(new Customer(4L, "Jennifer", "Thursday"));
        customers.addCustomer(new Customer(5L, "Jenny", "Friday"));
    }

    @RequestMapping(value = "/Hello", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HelloWorld helloWorld() {
        return new HelloWorld();
    }

    @RequestMapping(value = "/Customers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<Customer> getCustomers() {
        return customers.getCustomerSet();

    }

    @RequestMapping(value = "/Customer/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") Long id) {
        ResponseEntity<Customer> customerResponseEntity = new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
        ;

        for (Customer customer :
                customers.getCustomerSet()) {
            if (customer.getId() == id) {
                customerResponseEntity = new ResponseEntity<>(customer, HttpStatus.OK);
            }

        }
        return customerResponseEntity;

    }
}

//    @RequestMapping(value = "/Customer/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    public Customer customer() {
//        for(Customer customer: customers.getCustomerSet()) {
//        if (customer.getId().equals(@PathVariable("id"))) {
//    }
//
//
//}
