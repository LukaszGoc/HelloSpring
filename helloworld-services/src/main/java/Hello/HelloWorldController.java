package Hello;


import Hello.Data.Customers;
import Hello.Model.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
public class HelloWorldController {

    private static Set<Customer> testCustomersSet= new HashSet<Customer>();

    private static Customers customers = Customers.getInstance();
    private static Long count = 0L;

    static {
        customers.addCustomer(new Customer(1L, "John", "Monday"));
        customers.addCustomer(new Customer(2L, "Johny", "Tuesday"));
        customers.addCustomer(new Customer(3L, "Johnathan", "Wednesday"));
        customers.addCustomer(new Customer(4L, "Jennifer", "Thursday"));
        customers.addCustomer(new Customer(5L, "Jenny", "Friday"));
        testCustomersSet.add(new Customer(1L, "John", "Monday"));
        testCustomersSet.add(new Customer(2L, "Johny", "Tuesday"));
        testCustomersSet.add(new Customer(3L, "Johnathan", "Wednesday"));
        testCustomersSet.add(new Customer(4L, "Jennifer", "Thursday"));
        testCustomersSet.add(new Customer(5L, "Jenny", "Friday"));

        count = (long) customers.getCustomerSet().size();
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

    @RequestMapping(value = "/Customer/add/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> createCustomer(@PathVariable("id") Long id) {
        customers.addCustomer(new Customer(id, null, null));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/Customers",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
//        count++;
//        customers.addCustomer(customer);
        testCustomersSet.add(customer);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
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
