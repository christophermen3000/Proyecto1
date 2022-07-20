package pe.com.nttdata.notificacion.service;

import pe.com.nttdata.clientefeign.notificacion.NotificacionRequest;

public interface INotificacionService {
    public boolean enviarNotificacion(NotificacionRequest notificacionRequest);
}
