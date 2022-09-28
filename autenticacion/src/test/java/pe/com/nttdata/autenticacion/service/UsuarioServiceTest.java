package pe.com.nttdata.autenticacion.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import pe.com.nttdata.autenticacion.dao.IUsuarioDao;
import pe.com.nttdata.autenticacion.dto.NuevoUsuarioDto;
import pe.com.nttdata.autenticacion.dto.RequestDto;
import pe.com.nttdata.autenticacion.dto.TokenDto;
import pe.com.nttdata.autenticacion.dto.UsuarioDto;
import pe.com.nttdata.autenticacion.model.Usuario;
import pe.com.nttdata.autenticacion.security.JwtProvider;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@WebMvcTest(UsuarioServiceTest.class)
class UsuarioServiceTest {

    @InjectMocks
    UsuarioService usuarioService;

    @Mock
    IUsuarioDao usuarioDao;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    JwtProvider jwtProvider;

    @BeforeEach
    void setUp() {
        usuarioService = new UsuarioService(usuarioDao, passwordEncoder, jwtProvider);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void save() {
        Optional<Usuario> usuarioOptional = Optional.empty();
        when(usuarioDao.findByUsuario(anyString())).thenReturn(usuarioOptional);
        when(passwordEncoder.encode(anyString())).thenReturn("1234");

        NuevoUsuarioDto nuevoUsuarioDto = new NuevoUsuarioDto();
        nuevoUsuarioDto.setUsuario("administrador");
        nuevoUsuarioDto.setPassword("1234");
        nuevoUsuarioDto.setRol("admin");

        Usuario usuario = new Usuario();
        usuario.setId(1);
        usuario.setUsuario("administrador");
        usuario.setPassword("1234");
        usuario.setRol("admin");

        when(usuarioDao.save(any(Usuario.class))).thenReturn(usuario);

        Usuario usuarioResponse = usuarioService.save(nuevoUsuarioDto);

        assertNotNull(usuarioResponse);
        assertEquals(1, usuarioResponse.getId());
    }

    @Test
    void login() {
        Usuario usuario = new Usuario();
        usuario.setId(1);
        usuario.setUsuario("administrador");
        usuario.setPassword("1234");
        usuario.setRol("admin");
        when(usuarioDao.findByUsuario(anyString())).thenReturn(Optional.of(usuario));
        when(passwordEncoder.matches(any(), anyString())).thenReturn(true);
        when(jwtProvider.createToken(any(Usuario.class))).thenReturn("mytoken");

        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setUsuario("administrador");
        usuarioDto.setPassword("1234");
        TokenDto tokenDto = usuarioService.login(usuarioDto);

        assertNotNull(tokenDto);
        assertEquals("mytoken", tokenDto.getToken());
    }

    @Test
    void validate() {
        when(jwtProvider.validate(anyString(), any())).thenReturn(true);

        String username = "administrador";
        when(jwtProvider.getUserNameFromToken(anyString())).thenReturn(username);

        Usuario usuario = new Usuario();
        usuario.setId(1);
        usuario.setUsuario("administrador");
        usuario.setPassword("1234");
        usuario.setRol("admin");
        when(usuarioDao.findByUsuario(anyString())).thenReturn(Optional.of(usuario));

        String token = "mytoken";
        RequestDto requestDto = new RequestDto("uri", "method");
        TokenDto tokenDto = usuarioService.validate(token, requestDto);
        assertEquals("mytoken", tokenDto.getToken());
    }
}