package pe.com.nttdata.kafka.service;

import pe.com.nttdata.clientefeign.notificacionkafka.NotificacionKafkaRequest;

public interface INotificacionService {
    public boolean enviarNotificacion(NotificacionKafkaRequest notificacionRequest);
}
