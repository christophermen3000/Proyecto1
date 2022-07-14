package pe.com.nttdata.cliente.controller;

public record NotificacionRequest(Integer clienteId, String clienteEmail, String mensaje) {
}
