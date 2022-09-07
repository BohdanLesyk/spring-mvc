package software.sigma;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import software.sigma.dao.UserMapper;
import software.sigma.models.User;

import java.util.List;

@Component  // Bean
public class UserDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> index() {
        return jdbcTemplate.query("SELECT * FROM users", new UserMapper());
    }

    public User show (final int userId) {
        return jdbcTemplate.query("SELECT * FROM users WHERE id=?", new Object[]{userId}, new UserMapper())
                .stream().findAny().orElse(null);
    }

    public void save(User user) {
        jdbcTemplate.update("INSERT INTO users VALUES(1, ?, ?, ?)",
                user.getName(), user.getAge(), user.getEmail());

    }

    public void update(int id, User user) {
            jdbcTemplate.update("UPDATE users SET name=?, age=?, email=? WHERE id=?",
                    user.getName(), user.getAge(), user.getEmail(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM users WHERE id=?", id);
    }
}
