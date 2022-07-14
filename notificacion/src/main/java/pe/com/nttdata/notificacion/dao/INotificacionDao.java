package pe.com.nttdata.notificacion.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.nttdata.notificacion.model.Notificacion;

public interface INotificacionDao extends JpaRepository<Notificacion, Integer> {
}
