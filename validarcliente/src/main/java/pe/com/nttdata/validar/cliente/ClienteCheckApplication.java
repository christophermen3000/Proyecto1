package pe.com.nttdata.validar.cliente;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(
        basePackages = "pe.com.nttdata.clientefeign"
)
public class ClienteCheckApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClienteCheckApplication.class, args);
    }
}
