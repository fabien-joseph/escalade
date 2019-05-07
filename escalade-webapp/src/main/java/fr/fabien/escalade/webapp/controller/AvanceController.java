package fr.fabien.escalade.webapp.controller;

import fr.fabien.escalade.business.SiteManagement;
import fr.fabien.escalade.model.topo.Site;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;

@Controller
public class AvanceController {
    @Autowired
    SiteManagement siteManagement;

    @GetMapping("/test")
    public String test(Model model) {
        model.addAttribute("site", new Site());
        return "recherche";

    }

    @PostMapping("/testresult")
    public String result(@ModelAttribute Site site, Model model){
        if (site.getNom() == null) {
            site.setNom("");
        }
        if (site.getHauteurMin() == null) {
            site.setHauteurMin(0);
        }
        if (site.getHauteurMax() == null) {
            site.setHauteurMax(99999);
        }
        if (site.getDepartement() == null) {
            site.setDepartement("");
        }

        List<Site> sites = siteManagement.findSitesAdvanced(site.getHauteurMin(), site.getHauteurMax(), site.getNom(), site.getDepartement());

        model.addAttribute("sites", sites);
        return "result";
    }
}
