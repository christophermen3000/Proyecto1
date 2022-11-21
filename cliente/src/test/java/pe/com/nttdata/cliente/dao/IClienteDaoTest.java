package pe.com.nttdata.cliente.dao;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import pe.com.nttdata.cliente.model.Cliente;
import pe.com.nttdata.cliente.model.Pais;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(IClienteDaoTest.class)
class IClienteDaoTest {

    @Mock
    IClienteDao clienteDao;

    @Test
    void findByNombre() {
        List<Cliente> clientes = generarListaClientes();

        when(clienteDao.findByNombre(any(String.class))).thenReturn(clientes);
        List<Cliente> clientesResponse = clienteDao.findByNombre("nombre");
        assertEquals(2, clientesResponse.size());
    }

    @Test
    void findByApellidoPaterno() {
        List<Cliente> clientes = generarListaClientes();

        when(clienteDao.findByApellidoPaterno(any(String.class))).thenReturn(clientes);
        List<Cliente> clientesResponse = clienteDao.findByApellidoPaterno("apellidoPaterno");
        assertEquals(2, clientesResponse.size());
    }

    @Test
    void findByApellidoMaterno() {
        List<Cliente> clientes = generarListaClientes();

        when(clienteDao.findByApellidoMaterno(any(String.class))).thenReturn(clientes);
        List<Cliente> clientesResponse = clienteDao.findByApellidoMaterno("apellidoMaterno");
        assertEquals(2, clientesResponse.size());
    }

    @Test
    void findByFechaNacimiento() {
        List<Cliente> clientes = generarListaClientes();

        when(clienteDao.findByFechaNacimiento(any(LocalDate.class))).thenReturn(clientes);
        LocalDate date = LocalDate.of(2022,9,28);
        List<Cliente> clientesResponse = clienteDao.findByFechaNacimiento(date);
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
        cliente1.setId(2);
        cliente1.setNombre("mynombre2");
        cliente1.setEmail("myEmail2");
        cliente1.setPais(pais);
        cliente1.setApellidoPaterno("myApellidoPaterno2");
        cliente1.setApellidoMaterno("myApellidoMaterno2");
        cliente1.setFechaNacimiento(date);

        List<Cliente> clientes = new ArrayList<>();
        clientes.add(cliente1);
        clientes.add(cliente2);

        return clientes;
    }
}