package fr.fabien.escalade.webapp.controller;

import fr.fabien.escalade.business.TopoManagement;
import fr.fabien.escalade.business.UtilisateurManagement;
import fr.fabien.escalade.model.topo.Topo;
import lombok.RequiredArgsConstructor;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class RechercheController {
    @Autowired
    TopoManagement topoManagement;
    @Autowired
    UtilisateurManagement utilisateurManagement;

    @GetMapping("/recherche")
    @JsonIgnore
    public List<Topo> listTopos(Model model, @RequestParam(value = "departement", required = false) String departement) {
        List<Topo> topos;
        if (departement == null || departement.equals("")) {
            topos = topoManagement.findAll();
        } else {
            topos = topoManagement.findToposByDepartement(departement);
        }
        model.addAttribute("topos", topos);
        return topos;
    }
}
