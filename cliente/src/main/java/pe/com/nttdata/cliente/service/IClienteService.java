package pe.com.nttdata.cliente.service;

import pe.com.nttdata.cliente.controller.ClienteRequest;
import pe.com.nttdata.cliente.model.Cliente;

import java.time.LocalDate;
import java.util.List;

public interface IClienteService {
    public List<Cliente> listarClientes();
    public Cliente registrarCliente(Cliente cliente);
    public Cliente modificarCliente(Cliente cliente);
    public int eliminarCliente(Integer id);
    public Cliente obtenerCliente(Integer id);
    public List<Cliente> listarClientesPorNombre(String nombre);
    public List<Cliente> listarClientesPorApellidoPaterno(String apellidoPaterno);
    public List<Cliente> listarClientesPorApellidoMaterno(String apellidoMaterno);
    public List<Cliente> listarClientesPorFechaNacimiento(LocalDate fechaNacimiento);
}
