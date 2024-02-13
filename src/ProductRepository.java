
import java.util.List;

interface ProductRepository {
    Product findById(int id);
    List<Product> findAll();
    void save(Product product);
    void update(Product product);
    void delete(int id);
}
