package pe.com.nttdata.notificacion.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.nttdata.notificacion.controller.NotificacionRequest;
import pe.com.nttdata.notificacion.dao.INotificacionDao;
import pe.com.nttdata.notificacion.model.Notificacion;
import pe.com.nttdata.notificacion.service.INotificacionService;

import java.util.Date;

@Service
@AllArgsConstructor
public class NotificacionService implements INotificacionService {
    private final INotificacionDao notificacionDao;

    public boolean enviarNotificacion(NotificacionRequest notificacionRequest) {
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
