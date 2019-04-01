package fr.fabien.escalade.webapp.controller;

import fr.fabien.escalade.business.UtilisateurManagement;
import fr.fabien.escalade.model.topo.Utilisateur;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

import static fr.fabien.escalade.business.Departements.departements;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class AccueilController {
    @Autowired
    UtilisateurManagement utilisateurManagement;

    @GetMapping
    public String accueil(Model model, HttpServletRequest session) {
        Utilisateur utilisateur;
        if (session.getUserPrincipal() != null) {
            utilisateur = utilisateurManagement.findByLogin(session.getUserPrincipal().getName());
            model.addAttribute(utilisateur);
        }
        model.addAttribute("departements", departements);
        return "accueil";
    }
}
