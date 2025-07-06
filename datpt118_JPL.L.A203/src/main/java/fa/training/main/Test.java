package fa.training.main;

import fa.training.entities.Customer;
import fa.training.entities.Order;
import fa.training.services.CustomerService;
import fa.training.utils.Validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Test {
    private static final CustomerService customerService = new CustomerService();
    private static final Scanner sc = new Scanner(System.in);
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public static void main(String[] args) {
        List<Customer> customers = customerService.findAll();

        // Add sample data if empty
        if (customers == null || customers.isEmpty()) {
            List<Order> orders1 = new ArrayList<>();
            try {
                orders1.add(new Order("1234567890", sdf.parse("10/01/2023")));
                orders1.add(new Order("1112223334", sdf.parse("12/05/2023")));
            } catch (Exception e) {}

            List<Order> orders2 = new ArrayList<>();
            try {
                orders2.add(new Order("2223334445", sdf.parse("15/03/2022")));
            } catch (Exception e) {}

            customers.add(new Customer("Hoa", "0987766554", "Hanoi", orders1));
            customers.add(new Customer("Long", "0911222333", "HCM", orders2));

            customerService.save(customers);
            System.out.println("Sample data added!");
        }
        while (true) {
            System.out.println("\nChoose function:");
            System.out.println("1. Add a new Customer");
            System.out.println("2. Show all Customers");
            System.out.println("3. Search Customer");
            System.out.println("4. Remove Customer");
            System.out.println("5. Exit");
            System.out.print("Your choice: ");
            String ch = sc.nextLine();
            switch (ch) {
                case "1":
                    Customer c = inputCustomer();
                    customers.add(c);
                    customerService.save(customers);
                    System.out.println("Customer added.");
                    break;
                case "2":
                    customerService.display(customers);
                    break;
                case "3":
                    System.out.print("Enter phone to search: ");
                    String phone = sc.nextLine();
                    List<Customer> found = customerService.search(phone);
                    customerService.display(found);
                    break;
                case "4":
                    System.out.print("Enter phone to remove: ");
                    String ph = sc.nextLine();
                    if (customerService.remove(ph)) {
                        customers = customerService.findAll(); // reload data
                        System.out.println("Removed successfully!");
                    } else {
                        System.out.println("Not found!");
                    }
                    break;
                case "5":
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid!");
            }
        }
    }

    // Hàm nhập customer và order (lặp nhập order, validate)
    private static Customer inputCustomer() {
        System.out.println("--- Enter Customer information ---");
        System.out.print("Enter name: ");
        String name = sc.nextLine();

        String phone;
        while (true) {
            System.out.print("Enter phone: ");
            phone = sc.nextLine();
            if (Validator.isValidPhone(phone)) break;
            System.out.println("Invalid phone (must start with 0, 10-11 digits)!");
        }

        System.out.print("Enter address: ");
        String address = sc.nextLine();

        List<Order> orderList = new ArrayList<>();
        while (true) {
            System.out.println("Enter order info:");
            String number;
            while (true) {
                System.out.print("+ number (10 digits): ");
                number = sc.nextLine();
                if (Validator.isValidOrderNumber(number)) break;
                System.out.println("Invalid order number!");
            }
            Date date = null;
            while (date == null) {
                System.out.print("+ date (dd/MM/yyyy): ");
                String d = sc.nextLine();
                try {
                    date = sdf.parse(d);
                } catch (ParseException e) {
                    System.out.println("Invalid date format!");
                }
            }
            orderList.add(new Order(number, date));
            System.out.print("Continue add order? (n/N to stop): ");
            String stop = sc.nextLine();
            if ("n".equalsIgnoreCase(stop)) break;
        }
        sc.close();
        return new Customer(name, phone, address, orderList);
    }
}
