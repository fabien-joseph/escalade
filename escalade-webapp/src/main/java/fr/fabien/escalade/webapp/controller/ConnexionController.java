package fr.fabien.escalade.webapp.controller;

import fr.fabien.escalade.model.topo.Utilisateur;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ConnexionController {
    @GetMapping ("/connexion")
    public String connexion(Model model) {
        model.addAttribute("utilisateur", new Utilisateur());
        return "connexion";
    }

    @PostMapping("/connexion")
    public String connexionSubmit(@ModelAttribute Utilisateur utilisateur, Model model) {
        model.addAttribute(utilisateur);
        return "result";
    }
}
