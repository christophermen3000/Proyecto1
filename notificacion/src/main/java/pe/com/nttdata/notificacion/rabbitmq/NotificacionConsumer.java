package pe.com.nttdata.notificacion.rabbitmq;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import pe.com.nttdata.clientefeign.notificacion.NotificacionRequest;
import pe.com.nttdata.notificacion.service.INotificacionService;

@Component
@AllArgsConstructor
@Slf4j
public class NotificacionConsumer {
    private final INotificacionService notificacionService;

    @RabbitListener(queues = "${rabbitmq.queues.notification}")
    public void consumer(NotificacionRequest notificacionRequest) {
        log.info("Consumido {} desde cola", notificacionRequest);
        notificacionService.enviarNotificacion(notificacionRequest);
    }

}
