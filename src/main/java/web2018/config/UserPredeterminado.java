package web2018.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import web2018.model.Rol;
import web2018.model.Usuario;
import web2018.services.RolService;
import web2018.services.UsuarioService;

import java.time.LocalDate;

@Controller
public class UserPredeterminado {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RolService rolService;

    @Autowired
    public UserPredeterminado(UsuarioService usuarioService, RolService rolService) {
        Usuario usuario = new Usuario();
        usuario.setNombres("Shantall");
        usuario.setApellidos("Giron");
        usuario.setCorreo("shantgiron@gmail.com");
        usuario.setFechaNacimiento(LocalDate.of(1995, 5, 8));
        usuario.setRol("ROLE_ADMINISTRADOR");
        usuario.setNombreUsuario("admin");
        usuario.setContrasena("admin");
        usuario.setHabilitado(true);

        if (usuarioService.findAll().size() == 0) {
            usuarioService.save(usuario);
        }

        if (rolService.findAll().size() == 0) {
            Rol rol = new Rol();
            rol.setNombre("ROLE_ADMINISTRADOR");
            rol.setDescripcion("Este rol administra el programa.");
            rolService.save(rol);

            rol = new Rol();
            rol.setNombre("ROLE_USUARIO");
            rol.setDescripcion("Este rol puede visualizar las paginas pero no crear contenido.");
            rolService.save(rol);
        }
    }
}