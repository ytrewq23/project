
import java.util.List;

interface UserRepository {
    void save(User user);
    void delete(int userId);
    User findById(int userId);
    User findByName(String userName); // Добавленный метод
    List<User> findAll();
}

