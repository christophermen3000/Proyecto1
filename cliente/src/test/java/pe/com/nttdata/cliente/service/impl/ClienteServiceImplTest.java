package pe.com.nttdata.cliente.service.impl;

import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import pe.com.nttdata.cliente.dao.IClienteDao;
import pe.com.nttdata.cliente.kafka.producer.ClienteProducer;
import pe.com.nttdata.cliente.model.Cliente;
import pe.com.nttdata.cliente.model.Pais;
import pe.com.nttdata.clientefeign.notificacionkafka.NotificacionKafkaRequest;
import pe.com.nttdata.clientefeign.validar.cliente.ClienteCheckClient;
import pe.com.nttdata.clientefeign.validar.cliente.ClienteCheckResponse;
import pe.com.nttdata.clientequeues.rabbitmq.RabbitMQMessageProducer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@WebMvcTest(ClienteServiceImplTest.class)
class ClienteServiceImplTest {

    @InjectMocks
    ClienteServiceImpl clienteServiceImpl;

    @Mock
    IClienteDao clienteDao;

    @Mock
    ClienteCheckClient clienteCheckClient;

    @Mock
    RabbitMQMessageProducer rabbitMQMessageProducer;

    @Mock
    ClienteProducer clienteProducer;

    @BeforeEach
    void setUp() {
        clienteServiceImpl = new ClienteServiceImpl(clienteDao, clienteCheckClient, rabbitMQMessageProducer, clienteProducer);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void listarClientes() {
        List<Cliente> clientes = generarListaClientes();

        when(clienteDao.findAll()).thenReturn(clientes);
        List<Cliente> clientesResponse = clienteServiceImpl.listarClientes();
        assertEquals(2, clientesResponse.size());

    }

    @Test
    void registrarCliente() {
        Cliente cliente = generarCliente();

        when(clienteDao.save(any(Cliente.class))).thenReturn(cliente);
        Cliente clienteResponse = clienteServiceImpl.registrarCliente(cliente);

        assertEquals(1, clienteResponse.getId());
    }

    @Test
    void validarCliente() {
        Cliente cliente = generarCliente();

        ClienteCheckResponse clienteCheckResponse = new ClienteCheckResponse(false);
        when(clienteCheckClient.validarCliente(anyInt())).thenReturn(clienteCheckResponse);

        String respuesta = clienteServiceImpl.validarCliente(cliente);
        assertEquals("OK", respuesta);
    }

    @Test
    void registrarNotificacion() {
        Cliente cliente = generarCliente();

        doNothing().when(rabbitMQMessageProducer).publish(any(),anyString(),anyString());
        clienteServiceImpl.registrarNotificacion(cliente);
        verify(rabbitMQMessageProducer,times(1)).publish(any(),anyString(),anyString());
    }

    @Test
    void registrarNotificacionKafka() {
        Cliente cliente = generarCliente();

        doNothing().when(clienteProducer).enviarMensaje(any(NotificacionKafkaRequest.class));
        clienteServiceImpl.registrarNotificacionKafka(cliente);
        verify(clienteProducer,times(1)).enviarMensaje(any(NotificacionKafkaRequest.class));
    }

    @Test
    void modificarCliente() {
        Cliente cliente = generarCliente();

        when(clienteDao.save(any(Cliente.class))).thenReturn(cliente);
        Cliente clienteResponse = clienteServiceImpl.modificarCliente(cliente);
        assertEquals(1, clienteResponse.getId());
    }

    @Test
    void eliminarCliente() {
        doNothing().when(clienteDao).deleteById(anyInt());
        int clienteResponse = clienteServiceImpl.eliminarCliente(1);
        assertEquals(0, clienteResponse);
    }

    @Test
    void obtenerCliente() {
        Cliente cliente = generarCliente();

        when(clienteDao.findById(anyInt())).thenReturn(Optional.ofNullable(cliente));
        Cliente clienteResponse = clienteServiceImpl.obtenerCliente(1);
        assertEquals(1, clienteResponse.getId());
    }

    @Test
    void listarClientesPorNombre() {
        List<Cliente> clientes = generarListaClientes();

        when(clienteDao.findByNombre(anyString())).thenReturn(clientes);
        List<Cliente> clientesResponse = clienteServiceImpl.listarClientesPorNombre("mynombre");
        assertEquals(2, clientesResponse.size());
    }

    @Test
    void listarClientesPorApellidoPaterno() {
        List<Cliente> clientes = generarListaClientes();

        when(clienteDao.findByApellidoPaterno(anyString())).thenReturn(clientes);
        List<Cliente> clientesResponse = clienteServiceImpl.listarClientesPorApellidoPaterno("myapellidoPaterno");
        assertEquals(2, clientesResponse.size());
    }

    @Test
    void listarClientesPorApellidoMaterno() {
        List<Cliente> clientes = generarListaClientes();

        when(clienteDao.findByApellidoMaterno(anyString())).thenReturn(clientes);
        List<Cliente> clientesResponse = clienteServiceImpl.listarClientesPorApellidoMaterno("myapellidoMaterno");
        assertEquals(2, clientesResponse.size());
    }

    @Test
    void listarClientesPorFechaNacimiento() {
        List<Cliente> clientes = generarListaClientes();
        when(clienteDao.findByFechaNacimiento(any())).thenReturn(clientes);
        LocalDate date = LocalDate.of(2022,9,28);
        List<Cliente> clientesResponse = clienteServiceImpl.listarClientesPorFechaNacimiento(date);
        assertEquals(2, clientesResponse.size());
    }

    List generarListaClientes(){
        Pais pais = new Pais();
        pais.setId(1);
        pais.setNombre("Peru");

        Cliente cliente1 = new Cliente();
        cliente1.setId(1);
        cliente1.setNombre("mynombre1");
        cliente1.setEmail("myEmail1");
        cliente1.setPais(pais);
        cliente1.setApellidoPaterno("myApellidoPaterno1");
        cliente1.setApellidoMaterno("myApellidoMaterno1");
        LocalDate date = LocalDate.of(2022,9,28);
        cliente1.setFechaNacimiento(date);

        Cliente cliente2 = new Cliente();
        cliente2.setId(2);
        cliente2.setNombre("mynombre2");
        cliente2.setEmail("myEmail2");
        cliente2.setPais(pais);
        cliente2.setApellidoPaterno("myApellidoPaterno2");
        cliente2.setApellidoMaterno("myApellidoMaterno2");
        cliente2.setFechaNacimiento(date);

        List<Cliente> clientes = new ArrayList<>();
        clientes.add(cliente1);
        clientes.add(cliente2);

        return clientes;
    }

    Cliente generarCliente(){
        Pais pais = new Pais();
        pais.setId(1);
        pais.setNombre("Peru");

        Cliente cliente = new Cliente();
        cliente.setId(1);
        cliente.setNombre("mynombre1");
        cliente.setEmail("myEmail1");
        cliente.setPais(pais);
        cliente.setApellidoPaterno("myApellidoPaterno1");
        cliente.setApellidoMaterno("myApellidoMaterno1");
        LocalDate date = LocalDate.of(2022,9,28);
        cliente.setFechaNacimiento(date);

        return cliente;
    }
}