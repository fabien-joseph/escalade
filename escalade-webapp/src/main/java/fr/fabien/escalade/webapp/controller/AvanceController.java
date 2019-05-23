package fr.fabien.escalade.webapp.controller;

import fr.fabien.escalade.business.SiteManagement;
import fr.fabien.escalade.model.topo.Site;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static fr.fabien.escalade.business.Cotations.cotations;
import static fr.fabien.escalade.business.Departements.departements;

@Controller
public class AvanceController {
    @Autowired
    SiteManagement siteManagement;

    @GetMapping("/test")
    public String test(Model model) {
        model.addAttribute("site", new Site());
        return "reservation";

    }

    @GetMapping("/recherche")
    public String listSites(Model model,
                            @RequestParam(value = "departement", required = false, defaultValue = "") String departement,
                            @RequestParam(value = "nom", required = false, defaultValue = "") String nom,
                            @RequestParam(value = "hauteurMin", required = false, defaultValue = "") Integer hauteurMin,
                            @RequestParam(value = "hauteurMax", required = false, defaultValue = "") Integer hauteurMax,
                            @RequestParam(value = "cotationMin", required = false, defaultValue = "") Integer cotationMin,
                            @RequestParam(value = "cotationMax", required = false, defaultValue = "") Integer cotationMax) {
        List<Site> sites = siteManagement.findSitesAdvanced(
                hauteurMin, hauteurMax,
                cotationMin, cotationMax,
                nom, departement);


        //List<Site> sites = siteManagement.findSitesAdvancedTest(nom, departement, cotationMin, cotationMax);
        model.addAttribute("sites", sites);
        model.addAttribute("departements", departements);
        model.addAttribute("cotations", cotations);
        return "recherche";
    }
}
