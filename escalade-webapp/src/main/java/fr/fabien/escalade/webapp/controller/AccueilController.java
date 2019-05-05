package fr.fabien.escalade.webapp.controller;

import fr.fabien.escalade.business.SiteManagement;
import fr.fabien.escalade.business.UtilisateurManagement;
import fr.fabien.escalade.model.topo.Site;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.List;

import static fr.fabien.escalade.business.Departements.departements;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class AccueilController {
    @Autowired
    UtilisateurManagement utilisateurManagement;

    @Autowired
    SiteManagement siteManagement;

    @GetMapping
    public String accueil(Model model, HttpServletRequest request, HttpSession session) {
        session.setAttribute("user", utilisateurManagement.findByRequest(request));
        model.addAttribute("departements", departements);

        List<Site> list = siteManagement.findSitesAdvanced( 0, 200000, "", "");
        for (Site site : list) {
            System.out.println(site.getNom());
        }

        return "accueil";
    }
}
