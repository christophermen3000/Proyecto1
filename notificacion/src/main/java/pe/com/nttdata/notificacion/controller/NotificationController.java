package pe.com.nttdata.notificacion.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.com.nttdata.notificacion.service.INotificacionService;

@RestController
@RequestMapping("api/v1/notificacion")
@AllArgsConstructor
@Slf4j
public class NotificationController {
    private final INotificacionService notificacionService;

    @PostMapping
    public void enviarNotificacion(@RequestBody NotificacionRequest notificacionRequest) {
        log.info("Nueva notificacion... {}", notificacionRequest);
        notificacionService.enviarNotificacion(notificacionRequest);
    }
}
