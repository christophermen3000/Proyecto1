package pe.com.nttdata.cliente.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pe.com.nttdata.cliente.controller.ClienteCheckResponse;
import pe.com.nttdata.cliente.controller.ClienteRequest;
import pe.com.nttdata.cliente.controller.NotificacionRequest;
import pe.com.nttdata.cliente.dao.IClienteDao;
import pe.com.nttdata.cliente.model.Cliente;
import pe.com.nttdata.cliente.rabbitmq.RabbitMQMessageProducer;
import pe.com.nttdata.cliente.service.IClienteService;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class ClienteServiceImpl implements IClienteService {
    private final IClienteDao clienteDao;
    private final RestTemplate restTemplate;
    private final RabbitMQMessageProducer rabbitMQMessageProducer;

    public List<Cliente> listarClientes() {
        return clienteDao.findAll();
    }

    public Cliente registrarCliente(ClienteRequest clienteRequest) {
        Cliente cliente = Cliente.builder()
                .nombre(clienteRequest.nombre())
                .apellidoPaterno(clienteRequest.apellidoPaterno())
                .apellidoMaterno(clienteRequest.apellidoMaterno())
                .email(clienteRequest.email())
                .fechaNacimiento(clienteRequest.fechaNacimiento())
                .build();
        Cliente clienteResponse = clienteDao.save(cliente);

        ClienteCheckResponse clienteCheckResponse = restTemplate.getForObject(
                //"http://localhost:8081/api/v1/cliente-check/{clienteId}",
                "http://VALIDARCLIENTE/api/v1/cliente-check/{clienteId}",
                ClienteCheckResponse.class,
                clienteResponse.getId()
        );

        if (clienteCheckResponse.esEstafador()) {
            throw new IllegalStateException("Cliente es un estafador!!");
        }

        NotificacionRequest notificacionRequest = new NotificacionRequest(
                cliente.getId(),
                cliente.getEmail(),
                String.format("Hola %s, bienvenidos a NTTData...",
                        cliente.getNombre())
        );

        rabbitMQMessageProducer.publish(
                notificacionRequest,
                "internal.exchange",
                "internal.notification.routing-key"
        );

        return clienteResponse;
    }

    public Cliente modificarCliente(ClienteRequest clienteRequest) {
        Cliente cliente = Cliente.builder()
                .id(clienteRequest.id())
                .nombre(clienteRequest.nombre())
                .apellidoPaterno(clienteRequest.apellidoPaterno())
                .apellidoMaterno(clienteRequest.apellidoMaterno())
                .email(clienteRequest.email())
                .fechaNacimiento(clienteRequest.fechaNacimiento())
                .build();
        return clienteDao.save(cliente);
    }

    public int eliminarCliente(Integer id) {
        clienteDao.deleteById(id);
        return 0;
    }

    public Cliente obtenerCliente(Integer id) {
        return clienteDao.findById(id).get();
    }

    public List<Cliente> listarClientesPorNombre(String nombre) {
        return clienteDao.findByNombre(nombre);
    }

    public List<Cliente> listarClientesPorApellidoPaterno(String apellidoPaterno) {
        return clienteDao.findByApellidoPaterno(apellidoPaterno);
    }

    public List<Cliente> listarClientesPorApellidoMaterno(String apellidoMaterno) {
        return clienteDao.findByApellidoMaterno(apellidoMaterno);
    }

    public List<Cliente> listarClientesPorFechaNacimiento(Date fechaNacimiento) {
        return clienteDao.findByFechaNacimiento(fechaNacimiento);
    }
}
