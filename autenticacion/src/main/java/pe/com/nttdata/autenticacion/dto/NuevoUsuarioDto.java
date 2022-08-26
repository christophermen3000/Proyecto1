package pe.com.nttdata.autenticacion.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class NuevoUsuarioDto {
    private String usuario;
    private String password;
    private String rol;
}
