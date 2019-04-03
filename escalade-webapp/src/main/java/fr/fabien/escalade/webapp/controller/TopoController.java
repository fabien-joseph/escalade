package fr.fabien.escalade.webapp.controller;

import fr.fabien.escalade.business.*;
import fr.fabien.escalade.model.topo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequiredArgsConstructor
public class TopoController {
    @Autowired
    TopoManagement topoManagement;
    @Autowired
    SiteManagement siteManagement;
    @Autowired
    SecteurManagement secteurManagement;
    @Autowired
    VoieManagement voieManagement;
    @Autowired
    UtilisateurManagement utilisateurManagement;

    @GetMapping("/topo/")
    public String topo(Model model, HttpServletRequest session) {
        Topo topo = new Topo();
        model.addAttribute("utilisateur", utilisateurManagement.findByLogin(session.getUserPrincipal().getName()));
        model.addAttribute("topo", topo);
        return "creation_topo";
    }

    @PostMapping("/topo/save")
    public String creation_topo(@ModelAttribute Topo topo, Model model, HttpServletRequest session) {
        Utilisateur utilisateur = utilisateurManagement.findByLogin(session.getUserPrincipal().getName());
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        topo.setDate(date);
        topo.setUtilisateur(utilisateur);
        topoManagement.ajout(topo);

        String object_type = "topo";
        String link = "/" + object_type + "/" + utilisateur.getId();

        model.addAttribute("utilisateur", utilisateur);
        model.addAttribute("topo", topo);
        model.addAttribute("object_type", object_type);
        model.addAttribute("link", link);
        return "save_success";
    }

    @GetMapping("/topo/{id}")
    public String listMesTopos(Model model,
                               HttpServletRequest session, @PathVariable String id) {
        Utilisateur utilisateur = utilisateurManagement.findByLogin(session.getUserPrincipal().getName());
        Long long_id = Long.parseLong(id);
        model.addAttribute("utilisateur", utilisateur);
        model.addAttribute("topo", topoManagement.findTopoById(long_id));
        return "show_topo";
    }

    //@GetMapping("topo/{id}")


    /*
    @PostMapping("/topo/create")
    public String creation_topo(@ModelAttribute Topo topo, Model model, HttpServletRequest session) {
        Utilisateur utilisateur = utilisateurManagement.findByLogin(session.getUserPrincipal().getName());
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        Site site = new Site();
        System.out.println(topo.getId());
        topo.setDate(date);
        model.addAttribute("utilisateur", utilisateur);
        model.addAttribute(site);
        topo.setUtilisateur(utilisateur);
        topoManagement.ajout(topo);
        return "creation_site";
    }
*/
/*
    @GetMapping("/site/{id}")
    public String site(Model model, @PathVariable String id) {
        model.addAttribute("site", site);
        return "creation_site";
    }

    @PostMapping("/site")
    public String creation_site(@ModelAttribute Site site, Model model) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        site.setTopo(topo);
        site.setDate(date);
        siteManagement.ajout(site);
        this.site = site;
        return "creation_site";
    }

    @GetMapping("/secteur/{id}")
    public String secteur(Model model, @PathVariable String id) {
        model.addAttribute("secteur", secteur);
        return "creation_secteur";
    }

    @PostMapping("/secteur")
    public String creation_secteur(@ModelAttribute Secteur secteur, Model model) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        secteur.setSite(site);
        secteur.setDate(date);
        secteurManagement.ajout(secteur);
        this.secteur = secteur;
        return "creation_secteur";
    }

    @GetMapping("/voie/{id}")
    public String voie(Model model, @PathVariable String id) {
        model.addAttribute("voie", voie);
        return "creation_voie";
    }

    @PostMapping("/voie")
    public String creation_voie(@ModelAttribute Voie voie, Model model) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        voie.setSecteur(secteur);
        voie.setDate(date);
        voieManagement.ajout(voie);
        return "creation_voie";
    }
    */
}
