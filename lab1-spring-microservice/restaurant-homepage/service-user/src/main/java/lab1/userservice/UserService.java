package lab1.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class UserService {
    public static void main(String[] args) {
        SpringApplication.run(UserService.class, args);
    }
    @PostMapping("/api/user")
    public boolean checkUser(@RequestBody String userID) {
        if (Integer.parseInt(userID) < 100) {
            return true;
        } else {
            return false;
        }
    }
}
