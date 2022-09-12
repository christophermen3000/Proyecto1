package pe.com.nttdata.kafka.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.com.nttdata.clientefeign.notificacionkafka.NotificacionKafkaRequest;
import pe.com.nttdata.kafka.service.INotificacionService;

@RestController
@RequestMapping("api/v1/notificacionkafka")
@AllArgsConstructor
@Slf4j
public class NotificacionKafkaController {
    private final INotificacionService notificacionService;

    @PostMapping
    public void enviarNotificacion(@RequestBody NotificacionKafkaRequest notificacionKafkaRequest) {
        log.info("Nueva notificacion... {}", notificacionKafkaRequest);
        notificacionService.enviarNotificacion(notificacionKafkaRequest);
    }
}
