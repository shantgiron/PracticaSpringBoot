package web2018.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import web2018.model.Foto;
import web2018.model.Cliente;
import web2018.services.ClienteService;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.stream.Stream;

@Controller
public class ClientsController {

    @Autowired
    private ClienteService clienteService;

    @RequestMapping(value = "/clientes", method = RequestMethod.GET)
    public String get(Model model) {
        model.addAttribute("clientes", clienteService.findAll());
        model.addAttribute("error", "");

        return "clients";
    }

    @Secured({"ROLE_ADMINISTRADOR"})
    @RequestMapping(value = "/registrar/cliente", method = RequestMethod.POST)
    public String registrar(
            @RequestParam(value = "nombre") String nombre,
            @RequestParam(value = "cedula") String cedula,
            @RequestParam(value = "foto") MultipartFile foto,
            Model model
    )  throws Exception {
        Foto f = new Foto();
        f.setNombre(foto.getOriginalFilename());
        f.setData(foto.getBytes());

        Cliente cliente = new Cliente();
        cliente.setNombre(nombre);
        cliente.setCedula(cedula);
        cliente.setFoto(f);

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Stream violaciones = validator.validate(cliente).stream();

        if (violaciones.count() > 0) {
            violaciones = validator.validate(cliente).stream();

            model.addAttribute("clientes", clienteService.findAll());
            model.addAttribute("error", ((ConstraintViolation) violaciones.findFirst().get()).getMessage());

            return "clientes";
        } else {
            clienteService.save(cliente);

            return "redirect:/clientes";
        }
    }

    @RequestMapping(value = "/borrar/cliente", method = RequestMethod.GET)
    public String borrar(@RequestParam(name = "id") String id) {
        clienteService.delete(Long.parseLong(id));

        return "redirect:/clientes";
    }
}

