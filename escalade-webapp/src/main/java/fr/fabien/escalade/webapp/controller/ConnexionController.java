package fr.fabien.escalade.webapp.controller;

import fr.fabien.escalade.business.UtilisateurManagement;
import fr.fabien.escalade.business.ValidationModel;
import fr.fabien.escalade.model.topo.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ConnexionController {
    @Autowired
    UtilisateurManagement utilisateurManagement;
    @Autowired
    ValidationModel validationModel = new ValidationModel();

    @GetMapping ("/connexion")
    public String connexion(Model model, HttpServletRequest request, HttpSession session, @RequestParam(value = "errors", required = false) List<String> errors) {
        session.setAttribute("user", utilisateurManagement.findByRequest(request));
        model.addAttribute("errors", errors);
        return "connexion";
    }
    
    @GetMapping ("/inscription")
    public String inscription(Model model, @RequestParam(value = "errors", required = false) List<String> errors) {
        model.addAttribute("utilisateur_insc", new Utilisateur());
        model.addAttribute("errors", errors);
        return "inscription";
    }

    @PostMapping("/inscriptionform")
    public String inscriptionSubmit(@ModelAttribute Utilisateur utilisateur_insc, BindingResult result, RedirectAttributes ra){
        List<String> errors = validationModel.verifyValidity(utilisateur_insc);
        if (errors.size() != 0) {
            ra.addAttribute("errors", errors);
            return "redirect:/inscription";
        }
        utilisateurManagement.save(utilisateur_insc);
        return "redirect:/";
    }
}
