package software.sigma;

import org.springframework.stereotype.Component;
import software.sigma.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component  // Bean
public class UserDAO {
    private static final String CONNECTION_URL = "jdbc:postgresql://localhost:5432/postgres";//Here all configs would
    private static final String USERNAME = "postgres";//be better save in properties file in real projects
    private static final String PASSWORD = "postgres";

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            connection = DriverManager.getConnection(CONNECTION_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> index() {
        List<User> users = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM users";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                User user = new User();

                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setAge(resultSet.getInt("age"));
                user.setEmail(resultSet.getString("email"));

                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return users;
    }

    public User show (final int userId) {
        return null;
    }

    public void save(User user) {
        try {
            Statement statement = connection.createStatement();

            String query = "INSERT INTO users VALUES(" + 1 + ",'" + user.getName() + "'," +
                user.getAge() + ",'" + user.getEmail() + "');";

            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(int id, User user) { }

    public void delete(int id) { }
}
