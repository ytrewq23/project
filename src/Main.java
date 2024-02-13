
import java.sql.Connection;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import java.text.ParseException;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ProductRepository productRepository = new ProductRepositoryImpl();
    private static final OrderRepository orderRepository = new OrderRepositoryImpl();
    private static UserRepository userRepository;

    public static void main(String[] args) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            userRepository = new UserRepositoryImpl(connection);
        } catch (SQLException e) {
            System.err.println("Error connecting to the database: " + e.getMessage());
            return;
        }


        boolean exit = false;
        while (!exit) {
            System.out.println("Select action:");
            System.out.println("1. Add product");
            System.out.println("2. Remove the product");
            System.out.println("3. Show all products");
            System.out.println("4. Create an order");
            System.out.println("5. Show all orders");
            System.out.println("6. Add user");
            System.out.println("7. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    deleteProduct();
                    break;
                case 3:
                    showAllProducts();
                    break;
                case 4:
                    createOrder();
                    break;
                case 5:
                    showAllOrders();
                    break;
                case 6:
                    addUser();
                    break;
                case 7:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid input. Try again.");
            }
        }
    }

    private static void addProduct() {
        System.out.println("Enter the product name:");
        String name = scanner.nextLine();
        System.out.println("Enter the product description:");
        String description = scanner.nextLine();
        System.out.println("Enter the product price:");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Enter the product category:");
        String category = scanner.nextLine();

        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setCategory(category);

        productRepository.save(product);
        System.out.println("The product has been successfully added.");
    }
    private static void deleteProduct() {
        System.out.println("Enter the product ID to delete:");
        int id = scanner.nextInt();
        scanner.nextLine();

        productRepository.delete(id);
        System.out.println("Product with ID " + id + "successfully deleted.");
    }
    private static void showAllProducts() {
        List<Product> products = productRepository.findAll();
        System.out.println("List of all products:");
        for (Product product : products) {
            System.out.println(product.getId() + ": " + product.getName() + " - " + product.getPrice() + " tg ");
        }    }



    private static void createOrder() {
        System.out.println("Enter the user ID to create an order:");
        int userId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter the order date in the format \"dd-MM-yyyy\":");
        String dateInput = scanner.nextLine();
        Date date;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            date = dateFormat.parse(dateInput);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Use the \"dd-MM-yyyy\" format.");
            return;
        }

        System.out.println("Enter the delivery address:");
        String address = scanner.nextLine();

        Order order = new Order();
        order.setUserId(userId);
        order.setDate(date);
        order.setAddress(address);

        orderRepository.save(order);
        System.out.println("The order has been successfully created.");
    }
    private static void showAllOrders() {
        List<Order> orders = orderRepository.findAll();
        System.out.println("List of all orders:");
        for (Order order : orders) {
            System.out.println("Order #" + order.getId() + ":");
            System.out.println("User: " + userRepository.findById(order.getUserId()).getUsername());
            System.out.println("Delivery Address: " + order.getAddress());
            System.out.println("Products:");
            for (Product product : order.getProducts()) {
                System.out.println("- " + product.getName() + " - " + product.getPrice() + " tg");
            }
            System.out.println();
        }
    }



    private static void addUser() {
        System.out.println("Enter the user name:");
        String name = scanner.nextLine();
        System.out.println("Enter the user's email:");
        String email = scanner.nextLine();
        System.out.println("Enter the user ID:");
        int id = scanner.nextInt();

        User user = new User();
        user.setUsername(name);
        user.setEmail(email);
        user.setId(id);

        userRepository.save(user);
        System.out.println("User successfully added.");
    }
}
