package fr.fabien.escalade.webapp.controller;

import fr.fabien.escalade.business.ReservationManagement;
import fr.fabien.escalade.business.SiteManagement;
import fr.fabien.escalade.business.TopoManagement;
import fr.fabien.escalade.business.UtilisateurManagement;
import fr.fabien.escalade.model.topo.Reservation;
import fr.fabien.escalade.model.topo.Topo;
import fr.fabien.escalade.model.topo.Utilisateur;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class UtilisateurController {
    @Autowired
    UtilisateurManagement utilisateurManagement;
    @Autowired
    TopoManagement topoManagement;
    @Autowired
    SiteManagement siteManagement;
    @Autowired
    ReservationManagement reservationManagement;

    @GetMapping("/profil/{id}")
    public String profilOther(Model model, @PathVariable String id, HttpServletRequest request, HttpSession session) {
        Long long_id = Long.parseLong(id);
        Utilisateur utilisateur_show = null;
        Optional<Utilisateur> optional = utilisateurManagement.findById(long_id);
        if (optional.isPresent())
            utilisateur_show = optional.get();
        session.setAttribute("user", utilisateurManagement.findByRequest(request));
        model.addAttribute("utilisateur_show", utilisateur_show);
        model.addAttribute("topos", topoManagement.findToposByUtilisateur_id(long_id));
        model.addAttribute("sites", siteManagement.findSitesByUtilisateurId(long_id));

        return "profil";
    }

    @GetMapping("/profil")
    public String profil(Model model, HttpServletRequest request) {
        Utilisateur utilisateur = null;
        if (request.getUserPrincipal() != null)
            utilisateur = utilisateurManagement.findByLogin(request.getUserPrincipal().getName());
        List<Reservation> reservations = reservationManagement.findAllByUtilisateur_Id(utilisateur.getId());
        List<Topo> topos_reservations = new ArrayList<>();

        for (Reservation reservation :
                reservations) {
            topos_reservations.add(reservation.getTopo());
        }

        model.addAttribute("utilisateur_show", utilisateur);
        model.addAttribute("topos", topoManagement.findToposByUtilisateur_id(utilisateur.getId()));
        model.addAttribute("sites", siteManagement.findSitesByUtilisateurId(utilisateur.getId()));
        model.addAttribute("reservations", topos_reservations);

        return "profil";
    }
}
