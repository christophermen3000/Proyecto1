package pe.com.nttdata.clientefeign.notificacionkafka;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "notificacionkafka",
        url = "${clientefeign.notificacionkafka.url}"
)
public interface NotificacionKafkaClient {
    @PostMapping(path = "api/v1/notificacionkafka")
    void enviarNotificacion(@RequestBody NotificacionKafkaRequest notificacionKafkaRequest);
}
