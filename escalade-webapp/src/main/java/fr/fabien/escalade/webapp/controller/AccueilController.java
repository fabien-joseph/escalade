package fr.fabien.escalade.webapp.controller;

import fr.fabien.escalade.business.ReservationManagement;
import fr.fabien.escalade.business.SiteManagement;
import fr.fabien.escalade.business.TopoManagement;
import fr.fabien.escalade.business.UtilisateurManagement;
import fr.fabien.escalade.model.topo.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static fr.fabien.escalade.business.Departements.departements;

@Controller
@RequiredArgsConstructor
public class AccueilController {
    @Autowired
    UtilisateurManagement utilisateurManagement;

    @GetMapping("/")
    public String accueil(Model model, HttpServletRequest request, HttpSession session) {
        session.setAttribute("user", utilisateurManagement.findByRequest(request));
        model.addAttribute("departements", departements);
        return "accueil";
    }
}
