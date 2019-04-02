package fr.fabien.escalade.webapp.controller;

import fr.fabien.escalade.business.SiteManagement;
import fr.fabien.escalade.business.TopoManagement;
import fr.fabien.escalade.business.UtilisateurManagement;
import fr.fabien.escalade.model.topo.Utilisateur;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class UtilisateurController {
    @Autowired
    UtilisateurManagement utilisateurManagement;
    @Autowired
    TopoManagement topoManagement;
    @Autowired
    SiteManagement siteManagement;

    @GetMapping("/profile/{id}")
    public String profile(Model model, @PathVariable String id) {
        Long long_id = Long.parseLong(id);
        System.out.println("Long id: " + long_id);
        Utilisateur utilisateur = utilisateurManagement.findById(long_id);
        model.addAttribute("utilisateur", utilisateur);
        model.addAttribute("topos", topoManagement.findToposByUtilisateur_id(long_id));
        model.addAttribute("sites", siteManagement.findSitesByTopo_id(long_id));

        return "profile";
    }

    @GetMapping("/profile")
    public String profile(Model model, HttpServletRequest session) {
        Utilisateur utilisateur = utilisateurManagement.findByLogin(session.getUserPrincipal().getName());
        model.addAttribute("utilisateur", utilisateur);
        model.addAttribute("topos", topoManagement.findToposByUtilisateur_id(utilisateur.getId()));
        model.addAttribute("sites", siteManagement.findSitesByTopo_id(utilisateur.getId()));

        return "profile";
    }


}
