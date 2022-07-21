package pe.com.nttdata.cliente;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(
        scanBasePackages = {
                "pe.com.nttdata.cliente",
                "pe.com.nttdata.clientequeues"
        }
)
@EnableFeignClients(
        basePackages = "pe.com.nttdata.clientefeign"
)
public class ClienteApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClienteApplication.class, args);
    }
}
