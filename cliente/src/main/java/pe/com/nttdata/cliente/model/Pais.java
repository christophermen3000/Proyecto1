package pe.com.nttdata.cliente.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Pais {
    @Id
    @SequenceGenerator(
            name = "pais_id_sequence",
            sequenceName = "pais_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "pais_id_sequence"
    )
    @Column(name = "pais_id")
    private int id;

    @Column(name = "pais_nombre")
    private String nombre;
}
