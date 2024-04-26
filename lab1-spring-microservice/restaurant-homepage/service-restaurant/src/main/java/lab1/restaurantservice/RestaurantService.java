package lab1.restaurantservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class RestaurantService {
    private static int orderNumber = 0;
    public static void main(String[] args) {
        SpringApplication.run(RestaurantService.class, args);
    }
    @PostMapping("/api/order")
    public String processOrder(@RequestBody OrderRequest orderRequest) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Boolean> response = restTemplate.postForEntity("http://localhost:5001/api/user", orderRequest.getUserID(), Boolean.class);

        if (Boolean.TRUE.equals(response.getBody())) {
            orderNumber += 1;
            return "Order for user " + orderRequest.getUserID() + " confirmed!" +
                    "\nOrder text: " + orderRequest.getOrderText() +
                    "\nOrder number: " + orderNumber;
        } else {
            return "Order for user " + orderRequest.getUserID() + " denied!";
        }
    }
}
