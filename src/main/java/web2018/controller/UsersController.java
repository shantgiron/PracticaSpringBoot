package web2018.controller;

import web2018.model.Usuario;
import web2018.services.RolService;
import web2018.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Locale;

@Controller
public class UsersController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RolService rolService;

    @RequestMapping(value = "/usuarios", method = RequestMethod.GET)
    public String get(Locale locale, Model model) {
        model.addAttribute("usuarios", usuarioService.findAll());
        model.addAttribute("roles", rolService.findAll());

        return "users";
    }

    @Secured({"ROLE_ADMINISTRADOR"})
    @RequestMapping(value = "/registrar/usuario", method = RequestMethod.POST)
    public String registrar(
            @RequestParam(value = "nombres") String nombres,
            @RequestParam(value = "apellidos") String apellidos,
            @RequestParam(value = "fechaNacimiento") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaNacimiento,
            @RequestParam(value = "nombreUsuario") String nombreUsuario,
            @RequestParam(value = "contrasena") String contrasena,
            @RequestParam(value = "rol") String rol,
            @RequestParam(value = "correo") String correo
    ) {
        Usuario usuario = new Usuario();
        usuario.setNombres(nombres);
        usuario.setApellidos(apellidos);
        usuario.setFechaNacimiento(fechaNacimiento);
        usuario.setNombreUsuario(nombreUsuario);
        usuario.setContrasena(contrasena);
        usuario.setRol(rol);
        usuario.setCorreo(correo);
        usuario.setHabilitado(true);

        usuarioService.save(usuario);

        return "redirect:/usuarios";
    }

    @RequestMapping(value = "/borrar/usuario", method = RequestMethod.GET)
    public String borrar(@RequestParam(name = "id") String id) {
        usuarioService.delete(Long.parseLong(id));

        return "redirect:/usuarios";
    }

}
