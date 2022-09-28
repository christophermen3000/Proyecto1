package pe.com.nttdata.cliente.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pe.com.nttdata.cliente.model.Cliente;
import pe.com.nttdata.cliente.model.Pais;
import pe.com.nttdata.cliente.service.impl.ClienteServiceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@WebMvcTest(ClienteControllerTest.class)
class ClienteControllerTest {

    @InjectMocks
    ClienteController clienteController;

    @Mock
    ClienteServiceImpl clienteServiceImpl;

    @BeforeEach
    void setUp() {
        clienteController = new ClienteController(clienteServiceImpl);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void listarClientes() {
        List<Cliente> clientes = generarListaClientes();

        when(clienteServiceImpl.listarClientes()).thenReturn(clientes);
        ResponseEntity<?> responseEntity = clienteController.listarClientes();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        List<Cliente> clienteResponse = (List<Cliente>) responseEntity.getBody();
        assertEquals(2, clienteResponse.size());
    }

    @Test
    void obtenerCliente() {
        Cliente cliente = generarCliente();

        when(clienteServiceImpl.obtenerCliente(anyInt())).thenReturn(cliente);
        ResponseEntity<?> responseEntity = clienteController.obtenerCliente(1);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        Cliente clienteResponse = (Cliente) responseEntity.getBody();
        assertEquals(1, clienteResponse.getId());
    }

    @Test
    void registrarCliente() {
        Cliente cliente = generarCliente();

        when(clienteServiceImpl.registrarCliente(any(Cliente.class))).thenReturn(cliente);
        when(clienteServiceImpl.validarCliente(any(Cliente.class))).thenReturn("OK");
        doNothing().when(clienteServiceImpl).registrarNotificacion(any(Cliente.class));
        doNothing().when(clienteServiceImpl).registrarNotificacionKafka(any(Cliente.class));

        ResponseEntity<?> responseEntity = clienteController.registrarCliente(cliente);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        ClienteRequest clienteResponse = (ClienteRequest) responseEntity.getBody();
        assertEquals(1, clienteResponse.id());
    }

    @Test
    void modificarCliente() {
        Cliente cliente = generarCliente();

        when(clienteServiceImpl.modificarCliente(any(Cliente.class))).thenReturn(cliente);
        ResponseEntity<?> responseEntity = clienteController.modificarCliente(cliente);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        Cliente clienteResponse = (Cliente) responseEntity.getBody();
        assertEquals(1, clienteResponse.getId());
    }

    @Test
    void eliminarCliente() {
        when(clienteServiceImpl.eliminarCliente(anyInt())).thenReturn(0);
        clienteController.eliminarCliente(1);
        verify(clienteServiceImpl,times(1)).eliminarCliente(anyInt());
    }

    @Test
    void listarClientesPorNombre() {
        List<Cliente> clientes = generarListaClientes();

        when(clienteServiceImpl.listarClientesPorNombre(anyString())).thenReturn(clientes);
        ResponseEntity<?> responseEntity = clienteController.listarClientesPorNombre("mynombre");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        List<Cliente> clienteResponse = (List<Cliente>) responseEntity.getBody();
        assertEquals(2, clienteResponse.size());
    }

    @Test
    void listarClientesPorApellidoPaterno() {
        List<Cliente> clientes = generarListaClientes();

        when(clienteServiceImpl.listarClientesPorApellidoPaterno(anyString())).thenReturn(clientes);
        ResponseEntity<?> responseEntity = clienteController.listarClientesPorApellidoPaterno("myApellidoPaterno");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        List<Cliente> clienteResponse = (List<Cliente>) responseEntity.getBody();
        assertEquals(2, clienteResponse.size());
    }

    @Test
    void listarClientesPorApellidoMaterno() {
        List<Cliente> clientes = generarListaClientes();

        when(clienteServiceImpl.listarClientesPorApellidoMaterno(anyString())).thenReturn(clientes);
        ResponseEntity<?> responseEntity = clienteController.listarClientesPorApellidoMaterno("myApellidoMaterno");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        List<Cliente> clienteResponse = (List<Cliente>) responseEntity.getBody();
        assertEquals(2, clienteResponse.size());
    }

    @Test
    void listarClientesPorFechaNacimiento() {
        List<Cliente> clientes = generarListaClientes();

        when(clienteServiceImpl.listarClientesPorFechaNacimiento(any())).thenReturn(clientes);
        LocalDate date = LocalDate.of(2022,9,28);
        ResponseEntity<?> responseEntity = clienteController.listarClientesPorFechaNacimiento(date);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        List<Cliente> clienteResponse = (List<Cliente>) responseEntity.getBody();
        assertEquals(2, clienteResponse.size());
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