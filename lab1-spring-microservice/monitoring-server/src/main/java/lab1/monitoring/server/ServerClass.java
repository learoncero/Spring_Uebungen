package lab1.monitoring.server;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAdminServer // sagt, dass Monitoring Server gestartet werden soll
public class ServerClass {
    public static void main(String[] args) {
        SpringApplication.run(ServerClass.class, args);
    }
}
