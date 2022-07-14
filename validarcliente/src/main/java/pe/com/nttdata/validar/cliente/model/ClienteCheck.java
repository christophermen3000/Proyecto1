package pe.com.nttdata.validar.cliente.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ClienteCheck {
    @Id
    @SequenceGenerator(
            name = "clientecheck_id_sequence",
            sequenceName = "clientecheck_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "clientecheck_id_sequence"
    )
    private Integer id;
    private Integer clienteId;
    private Boolean esEstafador;
    private Date fechaCreacion;
}
