package pe.com.nttdata.cliente.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.nttdata.cliente.model.Cliente;
import pe.com.nttdata.cliente.service.IClienteService;

import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/cliente")
@AllArgsConstructor
public class ClienteController {

    private final IClienteService clienteService;

    @GetMapping
    public ResponseEntity<?> listarClientes() {
        List<Cliente> clientes = clienteService.listarClientes();
        log.info("listar clientes");
        return new ResponseEntity<>(clientes, clientes.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> obtenerCliente(@PathVariable("id") Integer id) {
        log.info("obtener cliente: ", id);
        return new ResponseEntity<>(clienteService.obtenerCliente(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> registrarCliente(@RequestBody ClienteRequest clienteRequest) {
        log.info("nuevo registro de cliente {}", clienteRequest);
        Cliente cliente = clienteService.registrarCliente(clienteRequest);
        return new ResponseEntity<ClienteRequest>(new ClienteRequest(cliente.getId(), clienteRequest.nombre(), clienteRequest.apellidoPaterno(), clienteRequest.apellidoMaterno() , clienteRequest.email(), clienteRequest.fechaNacimiento()), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> modificarCliente(@RequestBody ClienteRequest clienteRequest) {
        log.info("modificar datos de cliente {}", clienteRequest);
        clienteService.modificarCliente(clienteRequest);
        return new ResponseEntity<ClienteRequest>(clienteRequest, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public void eliminarCliente(@PathVariable("id") Integer id) {
        log.info("eliminar cliente: ", id);
        clienteService.eliminarCliente(id);
    }

    @GetMapping(params="nombre")
    public ResponseEntity<?> listarClientesPorNombre(@RequestParam String nombre) {
        List<Cliente> clientes = clienteService.listarClientesPorNombre(nombre);
        log.info("listar clientes por nombre: ",nombre);
        return new ResponseEntity<>(clientes, clientes.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping(params="apellidoPaterno")
    public ResponseEntity<?> listarClientesPorApellidoPaterno(@RequestParam String apellidoPaterno) {
        List<Cliente> clientes = clienteService.listarClientesPorApellidoPaterno(apellidoPaterno);
        log.info("listar clientes por apellido paterno: ", apellidoPaterno);
        return new ResponseEntity<>(clientes, clientes.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping(params="apellidoMaterno")
    public ResponseEntity<?> listarClientesPorApellidoMaterno(@RequestParam String apellidoMaterno) {
        List<Cliente> clientes = clienteService.listarClientesPorApellidoMaterno(apellidoMaterno);
        log.info("listar clientes por apellido materno:", apellidoMaterno);
        return new ResponseEntity<>(clientes, clientes.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping(params="fechaNacimiento")
    public ResponseEntity<?> listarClientesPorFechaNacimiento(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaNacimiento) {
        List<Cliente> clientes = clienteService.listarClientesPorFechaNacimiento(fechaNacimiento);
        log.info("listar clientes por fecha de nacimiento: ", fechaNacimiento);
        return new ResponseEntity<>(clientes, clientes.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

}
