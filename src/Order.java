
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
    private int id;
    private java.util.Date Date;
    private int userId;
    private String address;
    private List<Product> products;


    public Order() {

    }

    public Order(int id, Date Date, int userId, String address) {
        this.id = id;
        this.Date = Date;
        this.userId = userId;
        this.address = address;
    }

    public void addProduct(Product product) {
        if (products == null) {
            products = new ArrayList<>();
        }
        products.add(product);
    }

    public List<Product> getProducts() {
        return products;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return Date;
    }

    public void setDate(Date orderDate) {
        this.Date = orderDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

