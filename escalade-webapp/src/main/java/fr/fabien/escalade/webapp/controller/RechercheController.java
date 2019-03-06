package fr.fabien.escalade.webapp.controller;

import fr.fabien.escalade.business.Departements;
import fr.fabien.escalade.business.topo.TopoManagement;
import fr.fabien.escalade.model.topo.Topo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/recherche")
public class RechercheController {
    @Autowired
    TopoManagement topoManagement;

    @GetMapping
    public String listTopos(Model model, @RequestParam(value = "departement", required = false) String departement) {
        List<Topo> topos = null;
        if (departement == null || departement.equals("")) {
            topos = topoManagement.findAll();
        } else {
            topos = topoManagement.findToposByDepartement(departement);
        }

        model.addAttribute("topos", topos);
        return "recherche";
    }
}
