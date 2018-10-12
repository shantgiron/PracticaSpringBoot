package web2018.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import web2018.model.Equipo;
import web2018.services.EquipoService;

@Controller
public class CranesController {

    @Autowired
    private EquipoService equipoService;

    @RequestMapping(value = "/equipos", method = RequestMethod.GET)
    public String get(Model model) {
        model.addAttribute("equipos", equipoService.findAll());

        return "cranes";
    }

    @Secured({"ROLE_ADMINISTRADOR"})
    @RequestMapping(value = "/registrar/equipo", method = RequestMethod.POST)
    public String post(@RequestParam(name = "codigo") String codigo,
                       @RequestParam(name = "cantidadexistencia") int cantidadExistencia,
                       @RequestParam(name = "costoalquilerpordia") int costoAlquilerPorDia) {
        Equipo equipo = new Equipo();
        equipo.setCodigo(codigo);
        equipo.setCantidadExistencia(cantidadExistencia);
        equipo.setCostoAlquilerPorDia(costoAlquilerPorDia);
        equipo.setEntregado(false);

        equipoService.save(equipo);

        return "redirect:/equipos";
    }
}
