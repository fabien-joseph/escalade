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
    public String topo(Model model, HttpServletRequest request) {
        Utilisateur utilisateur = utilisateurManagement.findByRequest(request);
        Topo topo = new Topo();
        topo.setUtilisateur(utilisateur);
        model.addAttribute("user", utilisateur);
        model.addAttribute("topo", topo);
        return "creation";
    }

    @PostMapping("/topo/save")
    public String creation_topo(@ModelAttribute Topo topo, Model model, HttpServletRequest request) {
        Utilisateur utilisateur = utilisateurManagement.findByRequest(request);
        topoManagement.save(topo);
        model.addAttribute("utilisateur", utilisateur);
        model.addAttribute("topo", topo);
        model.addAttribute("object_type", "topo");
        return "redirect:/profile";
    }

    @GetMapping("/topo/{id}")
    public String listMesTopos(Model model,
                               HttpServletRequest request, @PathVariable String id) {
        Long long_id = Long.parseLong(id);
        model.addAttribute("utilisateur", utilisateurManagement.findByRequest(request));
        Optional<Topo> topoById = topoManagement.findById(long_id);
        model.addAttribute("topo", topoById.orElse(null));
        return "show";
    }

    @GetMapping("/site/")
    public String site(Model model, HttpServletRequest request) {
        Site site = new Site();
        site.setDate(new Date(System.currentTimeMillis()));
        Utilisateur utilisateur = utilisateurManagement.findByRequest(request);
        site.setUtilisateur(utilisateur);
        model.addAttribute("utilisateur", utilisateur);
        model.addAttribute("site", site);
        return "creation";
    }

    @PostMapping("/site/save")
    public String creation_site(@ModelAttribute Site site, Model model, HttpServletRequest request) {
        Utilisateur utilisateur = utilisateurManagement.findByRequest(request);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        site.setUtilisateur(utilisateur);
        siteManagement.save(site);

        String object_type = "site";
        model.addAttribute("utilisateur", utilisateur);
        model.addAttribute("site", site);
        model.addAttribute("object_type", object_type);
        return "redirect:/profile";
    }

    @GetMapping("/site/{id}")
    public String listMesSites(Model model,
                               HttpServletRequest request, @PathVariable String id, HttpSession session) {
        Long long_id = Long.parseLong(id);
        session.setAttribute("user", utilisateurManagement.findByRequest(request));
        model.addAttribute("site", siteManagement.findById(long_id).get());
        return "show";
    }

    @GetMapping("/site/{id}/secteur")
    public String creation_secteur(Model model, @PathVariable String id) {
        Secteur secteur = new Secteur();
        secteur.setDate(new Date(System.currentTimeMillis()));
        secteur.setSite(siteManagement.findById(Long.parseLong(id)).get());
        model.addAttribute("secteur", secteur);
        return "creation";
    }

    @GetMapping("/secteur/{id}")
    public String secteur(Model model, @PathVariable String id) {
        long long_id = Long.parseLong(id);
        model.addAttribute("secteur", secteurManagement.findSecteurById(long_id));
        return "show";
    }

    @PostMapping("/secteur/save")
    public String creation_secteur(@ModelAttribute Secteur secteur, Model model, HttpServletRequest request) {
        Utilisateur utilisateur = utilisateurManagement.findByRequest(request);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        secteur.setDate(date);
        secteurManagement.save(secteur);

        String object_type = "secteur";
        model.addAttribute("utilisateur", utilisateur);
        model.addAttribute("secteur", secteur);
        model.addAttribute("object_type", object_type);
        return "redirect:/profile";
    }

    @GetMapping("/secteur/{id}/voie")
    public String creation_voie(Model model, @PathVariable String id) {
        Voie voie = new Voie();
        voie.setDate(new Date(System.currentTimeMillis()));
        voie.setSecteur(secteurManagement.findSecteurById(Long.parseLong(id)));
        model.addAttribute("voie", voie);
        return "creation";
    }

    @GetMapping("/voie/{id}")
    public String voie(Model model, HttpServletRequest request, HttpSession session, @PathVariable String id) {
        long long_id = Long.parseLong(id);
        session.setAttribute("user", utilisateurManagement.findByRequest(request));
        model.addAttribute("voie", voieManagement.findVoieById(long_id));
        return "show";
    }

    @PostMapping("/voie/save")
    public String creation_voie(@ModelAttribute Voie voie, Model model, HttpServletRequest request) {
        Utilisateur utilisateur = utilisateurManagement.findByRequest(request);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        voie.setDate(date);
        voieManagement.save(voie);

        String object_type = "voie";
        model.addAttribute("utilisateur", utilisateur);
        model.addAttribute("voie", voie);
        model.addAttribute("object_type", object_type);
        return "redirect:/profile";
    }
}
