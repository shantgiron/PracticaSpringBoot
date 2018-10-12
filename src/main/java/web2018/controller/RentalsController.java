package web2018.controller;

import web2018.model.Alquiler;
import web2018.services.AlquilerService;
import web2018.services.ClienteService;
import web2018.services.EquipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import web2018.services.EquipoService;

import java.time.LocalDate;

@Controller
public class RentalsController {

    @Autowired
    private AlquilerService alquilerService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private EquipoService equipoService;

    @RequestMapping(value = "/alquileres", method = RequestMethod.GET)
    public String get(Model model) {
        model.addAttribute("alquileres", alquilerService.findAll());
        model.addAttribute("clientes", clienteService.findAll());
        model.addAttribute("equipos", equipoService.findAll());

        return "rentals";
    }

    @Secured({"ROLE_ADMINISTRADOR"})
    @RequestMapping(value = "/registrar/alquiler", method = RequestMethod.POST)
    public String post(@RequestParam(name = "cliente") long cliente,
                       @RequestParam(name = "fechaentrega") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaEntrega,
                       @RequestParam(name = "codigo") String codigo) {
        Alquiler alquiler = new Alquiler();
        alquiler.setEquipo(equipoService.findByCodigo(codigo));
        alquiler.setFechaRealizado(LocalDate.now());
        alquiler.setCliente(clienteService.findOne(cliente));
        alquiler.setFechaEntrega(fechaEntrega);

        alquilerService.save(alquiler);

        return "redirect:/alquileres";
    }
}
