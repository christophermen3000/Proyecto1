package pe.com.nttdata.validar.cliente.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.com.nttdata.clientefeign.validar.cliente.ClienteCheckResponse;
import pe.com.nttdata.validar.cliente.service.IClienteCheckService;

@Slf4j
@RestController
@RequestMapping("api/v1/cliente-check")
@AllArgsConstructor
public class ClienteCheckController {
    private final IClienteCheckService clienteCheckService;

    @GetMapping(path = "{clienteId}")
    public ClienteCheckResponse validarCliente(
            @PathVariable("clienteId") Integer clienteId) {
        boolean esEstafador = clienteCheckService.
                esClienteFraudulento(clienteId);
        log.info("validacion para cliente: {}", clienteId);

        return new ClienteCheckResponse(esEstafador);
    }
}
