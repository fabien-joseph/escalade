package fr.fabien.escalade.webapp.controller;

import fr.fabien.escalade.business.TopoManagement;
import fr.fabien.escalade.business.UtilisateurManagement;
import fr.fabien.escalade.model.topo.Topo;
import fr.fabien.escalade.model.topo.Utilisateur;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class RechercheController {
    @Autowired
    TopoManagement topoManagement;
    @Autowired
    UtilisateurManagement utilisateurManagement;

    @GetMapping("/recherche")
    public String listTopos(Model model, @RequestParam(value = "departement", required = false) String departement,
                            HttpServletRequest request, HttpSession session) {
        List<Topo> topos = null;
        if (departement == null || departement.equals("")) {
            topos = topoManagement.findAll();
        } else {
            topos = topoManagement.findToposByDepartement(departement);
        }
        session.setAttribute("user", utilisateurManagement.findBySession(request));
        model.addAttribute("topos", topos);
        return "recherche";
    }
}
