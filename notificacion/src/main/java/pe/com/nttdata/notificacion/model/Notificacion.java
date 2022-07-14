package pe.com.nttdata.notificacion.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Notificacion {
    @Id
    @SequenceGenerator(
            name = "notificacion_id_sequence",
            sequenceName = "notificacion_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "notificacion_id_sequence"
    )
    private Integer notificacionId;
    private Integer clienteId;
    private String clienteEmail;
    private String remitente;
    private String mensaje;
    private Date horaEnvio;
}
