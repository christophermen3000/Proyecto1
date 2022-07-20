package pe.com.nttdata.clientefeign.validar.cliente;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("validarcliente")
public interface ClienteCheckClient {
    @GetMapping(path = "api/v1/cliente-check/{clienteId}")
    ClienteCheckResponse validarCliente(@PathVariable("clienteId") Integer clienteId);
}
