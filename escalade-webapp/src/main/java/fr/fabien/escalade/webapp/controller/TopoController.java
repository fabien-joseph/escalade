package fr.fabien.escalade.webapp.controller;

import fr.fabien.escalade.business.SiteManagement;
import fr.fabien.escalade.business.TopoManagement;
import fr.fabien.escalade.model.topo.Secteur;
import fr.fabien.escalade.model.topo.Site;
import fr.fabien.escalade.model.topo.Topo;
import fr.fabien.escalade.model.topo.Voie;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequiredArgsConstructor
public class TopoController {
    @Autowired TopoManagement topoManagement;
    @Autowired SiteManagement siteManagement;

    @GetMapping ("/topo/{id}")
    public String topo(Model model, @PathVariable String id) {
        model.addAttribute("topo", new Topo());
        return "creation_topo";
    }

    @PostMapping("/topo")
    public String creation_topo(@ModelAttribute  Topo topo,Model model) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());

        topo.setDate(date);

        topoManagement.ajout(topo);
        return "creation_topo";
    }

    @GetMapping ("/site/{id}")
    public String site(Model model, @PathVariable String id) {
        model.addAttribute("site", new Site());
        return "creation_site";
    }

    @PostMapping("/site")
    public String creation_site(@ModelAttribute  Site site,Model model) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());

        site.setDate(date);

        siteManagement.ajout(site);
        return "creation_site";
    }
}
