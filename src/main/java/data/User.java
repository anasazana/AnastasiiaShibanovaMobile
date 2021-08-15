package data;

import java.util.Random;
import lombok.Getter;
import lombok.Setter;
import utils.PropertyReader;

@Getter
@Setter
public class User {
    private static User DEFAULT_USER;

    private final String email;
    private final String username;
    private final String password;

    private User(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public static User getRandomUser() {
        if (DEFAULT_USER == null) {
            DEFAULT_USER = new User(
                    new Random().nextInt(1000) + PropertyReader.getProperty("default.email"),
                    PropertyReader.getProperty("default.username"),
                    PropertyReader.getProperty("default.pwd")
            );
        }
        return DEFAULT_USER;
    }
}
