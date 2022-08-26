package pe.com.nttdata.autenticacion.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class Usuario {
    @Id
    @SequenceGenerator(
            name = "usuario_id_sequence",
            sequenceName = "usuario_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "usuario_id_sequence"
    )
    private Integer id;
    private String usuario;
    private String password;
    private String rol;
}
