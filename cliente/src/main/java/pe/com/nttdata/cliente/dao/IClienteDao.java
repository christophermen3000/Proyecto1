package pe.com.nttdata.cliente.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.nttdata.cliente.model.Cliente;

import java.util.Date;
import java.util.List;

public interface IClienteDao extends JpaRepository<Cliente, Integer> {

    List<Cliente> findByNombre(String nombre);
    List<Cliente> findByApellidoPaterno(String apellidoPaterno);
    List<Cliente> findByApellidoMaterno(String apellidoMaterno);
    List<Cliente> findByFechaNacimiento(Date fechaNacimiento);
}
