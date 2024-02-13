
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderRepositoryImpl implements OrderRepository {
    @Override
    public void save(Order order) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            if (order.getDate() == null) {

                System.out.println("The order date is null. The order will not be saved.");
                return;
            }
            String sql = "INSERT INTO orders (date, user_id, address) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setDate(1, new java.sql.Date(order.getDate().getTime()));
                statement.setInt(2, order.getUserId());
                statement.setString(3, order.getAddress());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int orderId) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "DELETE FROM orders WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, orderId);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Order findById(int orderId) {
        Order order = null;
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM orders WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, orderId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        Date date = resultSet.getDate("date");
                        int userId = resultSet.getInt("user_id");
                        String address = resultSet.getString("address");
                        order = new Order(orderId, date, userId, address);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public List<Order> findAll() {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM orders";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    Date date = resultSet.getDate("date");
                    int userId = resultSet.getInt("user_id");
                    String address = resultSet.getString("address");
                    Order order = new Order(id, date, userId, address);
                    orders.add(order);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
}
