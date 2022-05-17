package software.sigma;

import org.springframework.stereotype.Component;
import software.sigma.models.User;

import java.util.LinkedList;
import java.util.List;

@Component  // Bean
public class UserDAO {
    private static int USERS_COUNT;
    private List<User> users;

    {
        users = new LinkedList<>();

        users.add(new User(++USERS_COUNT, "Tom"));
        users.add(new User(++USERS_COUNT, "Bred"));
        users.add(new User(++USERS_COUNT, "Jolie"));
        users.add(new User(++USERS_COUNT, "John"));
    }

    public List<User> index() {
        return users;
    }

    public User show (final int userId) {
        return users.stream().filter(user -> user.getId() == userId).findAny().orElse(null);
    }
}
