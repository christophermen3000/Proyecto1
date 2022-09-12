package pe.com.nttdata.kafka.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.nttdata.kafka.model.Notificacion;

public interface INotificacionDao extends JpaRepository<Notificacion, Integer> {
}
