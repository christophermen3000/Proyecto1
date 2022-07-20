package pe.com.nttdata.clientefeign.notificacion;

public record NotificacionRequest(Integer clienteId, String clienteEmail, String mensaje) {
}
