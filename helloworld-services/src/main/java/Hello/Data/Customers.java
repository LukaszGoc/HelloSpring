package Hello.Data;

import Hello.Model.Customer;

import java.util.HashSet;
import java.util.Set;

public class Customers {
    final Set<Customer> customerSet = new HashSet<>();

    private static Customers instance;
    private Customers() {
    }


    public static Customers getInstance() {
        if(instance == null) {
            instance = new Customers();
        }
        return instance;
    }

    public Set<Customer> getCustomerSet() {
        return customerSet;
    }

    public boolean addCustomer(Customer customer) {
        return customerSet.add(customer);
    }

    public boolean deleteCustomer(Customer customer) {
        return customerSet.remove(customer);
    }
}
