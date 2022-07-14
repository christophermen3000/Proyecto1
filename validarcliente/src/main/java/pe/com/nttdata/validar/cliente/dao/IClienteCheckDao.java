package pe.com.nttdata.validar.cliente.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.nttdata.validar.cliente.model.ClienteCheck;

public interface IClienteCheckDao extends JpaRepository<ClienteCheck, Integer> {
}
