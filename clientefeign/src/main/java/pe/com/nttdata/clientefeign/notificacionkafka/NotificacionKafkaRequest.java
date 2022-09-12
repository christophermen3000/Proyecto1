package pe.com.nttdata.clientefeign.notificacionkafka;

public record NotificacionKafkaRequest(Integer clienteId, String clienteEmail, String mensaje) {
}
