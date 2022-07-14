package pe.com.nttdata.notificacion.service;

import pe.com.nttdata.notificacion.controller.NotificacionRequest;

public interface INotificacionService {
    public boolean enviarNotificacion(NotificacionRequest notificacionRequest);
}
