package web2018.controller;

import com.google.gson.Gson;
import web2018.model.Alquiler;
import web2018.services.AlquilerService;
import web2018.services.ClienteService;
import web2018.services.EquipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private EquipoService equipoService;

    @Autowired
    private AlquilerService alquilerService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String get(Model model) {
        model.addAttribute("clientes", clienteService.findAll().size());
        model.addAttribute("equipos", equipoService.findAll().size());
        model.addAttribute("alquileres", alquilerService.findAll().size());

        List<String> labels = new ArrayList<>();
        List<String> values = new ArrayList<>();
        for (Alquiler alquiler : alquilerService.findAll()) {
            labels.add(alquiler.getEquipo().getCodigo());
            values.add("" + alquiler.getTotal());
        }

        model.addAttribute("equipo_label", new Gson().toJson(labels));
        model.addAttribute("historiales", new Gson().toJson(values));

        return "index";
    }
}
