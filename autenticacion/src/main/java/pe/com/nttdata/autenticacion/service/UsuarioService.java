package pe.com.nttdata.autenticacion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pe.com.nttdata.autenticacion.dao.IUsuarioDao;
import pe.com.nttdata.autenticacion.dto.NuevoUsuarioDto;
import pe.com.nttdata.autenticacion.dto.RequestDto;
import pe.com.nttdata.autenticacion.dto.TokenDto;
import pe.com.nttdata.autenticacion.dto.UsuarioDto;
import pe.com.nttdata.autenticacion.model.Usuario;
import pe.com.nttdata.autenticacion.security.JwtProvider;

import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    IUsuarioDao usuarioDao;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtProvider jwtProvider;

    public Usuario save(NuevoUsuarioDto dto) {
        Optional<Usuario> usuario = usuarioDao.findByUsuario(dto.getUsuario());
        if(usuario.isPresent())
            return null;
        String password = passwordEncoder.encode(dto.getPassword());
        Usuario usuarioSave = Usuario.builder()
                .usuario(dto.getUsuario())
                .password(password)
                .rol(dto.getRol())
                .build();
        return usuarioDao.save(usuarioSave);
    }

    public TokenDto login(UsuarioDto dto) {
        Optional<Usuario> usuario = usuarioDao.findByUsuario(dto.getUsuario());
        if(!usuario.isPresent())
            return null;
        if(passwordEncoder.matches(dto.getPassword(), usuario.get().getPassword()))
            return new TokenDto(jwtProvider.createToken(usuario.get()));
        return null;
    }

    public TokenDto validate(String token, RequestDto dto) {
        if(!jwtProvider.validate(token, dto))
            return null;
        String usuario = jwtProvider.getUserNameFromToken(token);
        if(!usuarioDao.findByUsuario(usuario).isPresent())
            return null;
        return new TokenDto(token);
    }
}
