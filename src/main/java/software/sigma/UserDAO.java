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

        users.add(new User(++USERS_COUNT, "Tom", 24, "tom@gmail.com"));
        users.add(new User(++USERS_COUNT, "Bred", 53, "bred@gmail.com"));
        users.add(new User(++USERS_COUNT, "Jolie", 18, "jolie@gmail.com"));
        users.add(new User(++USERS_COUNT, "John", 35, "john@gmail.com"));
    }

    public List<User> index() {
        return users;
    }

    public User show (final int userId) {
        return users.stream().filter(user -> user.getId() == userId).findAny().orElse(null);
    }

    public void save(User user) {
        user.setId(++USERS_COUNT);
        users.add(user);
    }

    public void update(int id, User user) {
        User userToBeUpdated = show(id);

        userToBeUpdated.setName(user.getName());
        userToBeUpdated.setAge(user.getAge());
        userToBeUpdated.setEmail(user.getEmail());
    }

    public void delete(int id) {
        users.removeIf(user -> user.getId() == id);
    }
}
