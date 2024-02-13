
import java.util.List;
interface OrderRepository {
    void save(Order order);
    void delete(int orderId);
    Order findById(int orderId);
    List<Order> findAll();


}
