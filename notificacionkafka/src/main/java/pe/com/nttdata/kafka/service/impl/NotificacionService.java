package pe.com.nttdata.kafka.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.nttdata.clientefeign.notificacionkafka.NotificacionKafkaRequest;
import pe.com.nttdata.kafka.dao.INotificacionDao;
import pe.com.nttdata.kafka.model.Notificacion;
import pe.com.nttdata.kafka.service.INotificacionService;

import java.util.Date;

@Service
@AllArgsConstructor
public class NotificacionService implements INotificacionService {
    private final INotificacionDao notificacionDao;

    public boolean enviarNotificacion(NotificacionKafkaRequest notificacionRequest) {
        notificacionDao.save(
                Notificacion.builder()
                        .clienteId(notificacionRequest.clienteId())
                        .clienteEmail(notificacionRequest.clienteEmail())
                        .remitente("NTTData")
                        .mensaje(notificacionRequest.mensaje())
                        .horaEnvio(new Date())
                        .build()
        );
        return false;
    }
}
