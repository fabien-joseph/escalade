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
import javax.websocket.server.PathParam;
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
    public String topo(Model model, HttpServletRequest request) {
        Topo topo = new Topo();
        model.addAttribute("utilisateur", utilisateurManagement.findByLogin(request.getUserPrincipal().getName()));
        model.addAttribute("topo", topo);
        return "creation_topo";
    }

    @PostMapping("/topo/save")
    public String creation_topo(@ModelAttribute Topo topo, Model model, HttpServletRequest request) {
        Utilisateur utilisateur = utilisateurManagement.findByRequest(request);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        topo.setDate(date);
        topo.setUtilisateur(utilisateur);
        topoManagement.ajout(topo);

        String object_type = "topo";
        model.addAttribute("utilisateur", utilisateur);
        model.addAttribute("topo", topo);
        model.addAttribute("object_type", object_type);
        return "save_success";
    }

    @GetMapping("/topo/{id}")
    public String listMesTopos(Model model,
                               HttpServletRequest request, @PathVariable String id) {
        Utilisateur utilisateur = null;
        if (request.getUserPrincipal() != null)
            utilisateur = utilisateurManagement.findByLogin(request.getUserPrincipal().getName());
        Long long_id = Long.parseLong(id);
        model.addAttribute("utilisateur", utilisateur);
        model.addAttribute("topo", topoManagement.findTopoById(long_id));
        return "show_topo";
    }

    @GetMapping("/site/")
    public String site(Model model, HttpServletRequest request) {
        Site site = new Site();
        model.addAttribute("utilisateur", utilisateurManagement.findByLogin(request.getUserPrincipal().getName()));
        model.addAttribute("site", site);
        return "creation_site";
    }

    @PostMapping("/site/save")
    public String creation_site(@ModelAttribute Site site, Model model, HttpServletRequest request) {
        Utilisateur utilisateur = utilisateurManagement.findByRequest(request);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        site.setDate(date);
        site.setUtilisateur(utilisateur);
        siteManagement.ajout(site);

        String object_type = "site";
        model.addAttribute("utilisateur", utilisateur);
        model.addAttribute("site", site);
        model.addAttribute("object_type", object_type);
        return "save_success";
    }

    @GetMapping("/site/{id}")
    public String listMesSites(Model model,
                               HttpServletRequest request, @PathVariable String id, HttpSession session) {
        Long long_id = Long.parseLong(id);
        session.setAttribute("user", utilisateurManagement.findByRequest(request));
        model.addAttribute("site", siteManagement.findSiteById(long_id));
        return "show_site";
    }

    @GetMapping("/site/{id}/secteur")
    public String creation_secteur(Model model, @PathVariable String id, HttpServletRequest request, HttpSession session) {
        session.setAttribute("user", utilisateurManagement.findByRequest(request));
        Secteur secteur = new Secteur();
        secteur.setSite(siteManagement.findSiteById(Long.parseLong(id)));
        model.addAttribute("secteur", secteur);
        return "creation_secteur";
    }

    @GetMapping("/secteur/{id}")
    public String secteur(Model model, HttpServletRequest request, HttpSession session, @PathVariable String id) {
        long long_id = Long.parseLong(id);
        session.setAttribute("user", utilisateurManagement.findByRequest(request));
        model.addAttribute("secteur", secteurManagement.findSecteurById(long_id));
        return "show_secteur";
    }

    @PostMapping("/secteur/save")
    public String creation_secteur(@ModelAttribute Secteur secteur, Model model, HttpServletRequest request) {
        Utilisateur utilisateur = utilisateurManagement.findByRequest(request);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        secteur.setDate(date);
        System.out.println(secteur.getSite().getNom());
        secteurManagement.ajout(secteur);

        String object_type = "secteur";
        model.addAttribute("utilisateur", utilisateur);
        model.addAttribute("secteur", secteur);
        model.addAttribute("object_type", object_type);
        return "save_success";
    }

    @GetMapping("/secteur/{id}/voie")
    public String creation_voie(Model model, @PathVariable String id, HttpServletRequest request, HttpSession session) {
        session.setAttribute("user", utilisateurManagement.findByRequest(request));
        Voie voie = new Voie();
        voie.setSecteur(secteurManagement.findSecteurById(Long.parseLong(id)));
        model.addAttribute("voie", voie);
        return "creation_voie";
    }

    @GetMapping("/voie/{id}")
    public String voie(Model model, HttpServletRequest request, HttpSession session, @PathVariable String id) {
        long long_id = Long.parseLong(id);
        session.setAttribute("user", utilisateurManagement.findByRequest(request));
        model.addAttribute("voie", voieManagement.findVoieById(long_id));
        return "show_voie";
    }

    @PostMapping("/voie/save")
    public String creation_voie(@ModelAttribute Voie voie, Model model, HttpServletRequest request) {
        Utilisateur utilisateur = utilisateurManagement.findByRequest(request);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        voie.setDate(date);
        System.out.println(voie.getSecteur().getNom());
        voieManagement.ajout(voie);

        String object_type = "voie";
        model.addAttribute("utilisateur", utilisateur);
        model.addAttribute("voie", voie);
        model.addAttribute("object_type", object_type);
        return "save_success";
    }
}
