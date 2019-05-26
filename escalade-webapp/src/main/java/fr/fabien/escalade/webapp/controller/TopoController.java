package fr.fabien.escalade.webapp.controller;

import fr.fabien.escalade.business.*;
import fr.fabien.escalade.model.topo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static fr.fabien.escalade.business.Cotations.cotations;
import static fr.fabien.escalade.business.Departements.departements;

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
    @Autowired
    CommentaireManagement commentaireManagement;
    @Autowired
    ReservationManagement reservationManagement;
    @Autowired
    ValidationModel validationModel = new ValidationModel();


    @GetMapping("/topo")
    public String topo(Model model, HttpServletRequest request, @RequestParam(value = "errors", required = false) List<String> errors) {
        Utilisateur utilisateur = utilisateurManagement.findByRequest(request);
        Topo topo = new Topo();
        topo.setUtilisateur(utilisateur);
        model.addAttribute("user", utilisateur);
        model.addAttribute("topo", topo);
        model.addAttribute("errors", errors);
        return "topo_creation";
    }

    @PostMapping("/topo/save")
    public String creation_topo(Topo topo, Model model, HttpServletRequest request, RedirectAttributes ra) {
        List<String> errors = validationModel.verifyValidity(topo);

        if (errors.size() == 0) {
            Utilisateur utilisateur = utilisateurManagement.findByRequest(request);
            topoManagement.save(topo);
            model.addAttribute("utilisateur", utilisateur);
            model.addAttribute("topo", topo);
            model.addAttribute("object_type", "topo");
            return "redirect:/profile";
        }
        ra.addAttribute("errors", errors);
        return "redirect:/topo";
    }

    @GetMapping("/topo/{id}")
    public String listMesTopos(Model model, HttpServletRequest request, @PathVariable String id,
                               @RequestParam(value = "errors", required = false) List<String> errors) {
        try {
            Long long_id = Long.parseLong(id);
            if (topoManagement.findById(long_id).isPresent()) {
                Topo topo = topoManagement.findById(long_id).get();
                Commentaire commentaire = new Commentaire();
                List<Site> sites = siteManagement.findAllByTopos_Id(topo.getId());
                commentaire.setUtilisateur(utilisateurManagement.findByRequest(request));
                commentaire.setTopo(topo);

                if (reservationManagement.actualyReserved(topo).size() == 0 ||
                        reservationManagement.isReservedByUser(utilisateurManagement.findByRequest(request))) {
                    model.addAttribute("topo", topo);
                }
                if (utilisateurManagement.findByRequest(request) != null) {
                    if ((topo.getUtilisateur().getId().equals(utilisateurManagement.findByRequest(request).getId()))) {
                        model.addAttribute("topo", topo);
                    }
                }
                model.addAttribute("commentaire_write", commentaire);
                model.addAttribute("commentaires", commentaireManagement.findCommentairesByTopoId(long_id));
                model.addAttribute("redirectionId", id);
                model.addAttribute("dates", new Dates().getThisWeek());
                model.addAttribute("sites", sites);
                model.addAttribute("errors", errors);
                model.addAttribute("reservation", reservationManagement.findFirstByTopo(topoManagement.findById(Long.parseLong(id)).get()));
            }
        } catch (Exception ignored) {
        }
        return "topo_show";
    }

    @GetMapping("/topo/{id}/edit")
    public String topo_edit(Model model, @PathVariable String id) {
        try {
            Optional<Topo> topo = topoManagement.findById(Long.parseLong(id));
            topo.ifPresent(model::addAttribute);
            return "topo_creation";
        } catch (Exception ignored) {
        }
        return "erreur";
    }

    @PostMapping("/topo/{id}/reservation")
    public String reservation(@PathVariable String id, @RequestParam(value = "dateFin") String dateFin,
                              RedirectAttributes ra, HttpServletRequest request) {
        List<String> errors = new ArrayList<>();
        try {
            Long long_id = Long.parseLong(id);
            Optional<Topo> topo = topoManagement.findById(long_id);

            if (topo.isPresent()) {
                DateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
                Date dateConv = format.parse(dateFin);
                Reservation reservation = new Reservation();
                reservation.setDateDebut(new Date());
                reservation.setDateFin(dateConv);
                reservation.setUtilisateur(utilisateurManagement.findByRequest(request));
                reservation.setTopo(topo.get());
                if (reservationManagement.isFree(reservation)) {
                    reservationManagement.save(reservation);
                }
            } else {
                errors.add("Le topo que vous tentez de réserver n'existe pas");
            }
        } catch (Exception e) {
            errors.add("Le topo que vous tentez de réserver n'existe pas");
        }
        ra.addAttribute("errors", errors);
        return "redirect:/topo/{id}";
    }

    @GetMapping("reservation/{id}/cancel")
    public String reservationCancel(@PathVariable String id, RedirectAttributes ra, HttpServletRequest request) {
        List<String> errors = new ArrayList<>();
        try {
            Optional<Reservation> reservation = reservationManagement.findById(Long.parseLong(id));

            if (reservation.isPresent()) {
                if (reservation.get().getUtilisateur().getId().equals(utilisateurManagement.findByRequest(request).getId()) ||
                        reservation.get().getTopo().getUtilisateur().getId().equals(utilisateurManagement.findByRequest(request).getId()))
                    reservationManagement.delete(reservation.get());
            } else {
                errors.add("La réservation que vous tentez de supprimer n'existe pas");
            }
        } catch (Exception e) {
            errors.add("La réservation que vous tentez de supprimer n'existe pas");
        }
        ra.addAttribute("errors", errors);
        return "redirect:/profile";
    }

    @GetMapping("/topo/{id}/delete")
    public String topo_delete(@PathVariable String id, HttpServletRequest request) {
        try {
            Long long_id = Long.parseLong(id);
            Utilisateur utilisateur = utilisateurManagement.findByRequest(request);
            Optional<Topo> topo = topoManagement.findById(long_id);
            if (topo.isPresent()) {
                if (utilisateur.getId().equals(topo.get().getUtilisateur().getId())) {
                    if (topo.get().getCommentaires() != null)
                        commentaireManagement.deleteCommentairesByTopoId(topo.get().getId());
                    topoManagement.deleteById(long_id);
                }
            }
        } catch (Exception ignored) {
        }

        return "redirect:/profile";
    }

    @PostMapping("/topo/{id}/link")
    public String topoLinkSite(@PathVariable String id, @RequestParam("stringSite") String stringSite) {
        try {
            Site site = siteManagement.findSiteByNom(stringSite);
            Optional<Topo> topo = topoManagement.findById(Long.parseLong(id));

            if (topo.isPresent() && site != null) {
                if (!topoManagement.isAlreadyLinkWithTopo(topo.get(), site)) {
                    List<Site> sites = topo.get().getSites();
                    sites.add(site);
                    topo.get().setSites(sites);
                    topoManagement.save(topo.get());
                }
            }
            return "redirect:/topo/{id}";
        } catch (Exception e) {
            return "erreur";
        }
    }

    @GetMapping("/topo/{idTopo}/site/{idSite}/unlink")
    public String topoUnlinkSite(@PathVariable String idTopo, @PathVariable String idSite) {
        try {
            Optional<Topo> topo = topoManagement.findById(Long.parseLong(idTopo));
            if (topo.isPresent()) {
                Optional<Site> site = siteManagement.findById(Long.parseLong(idSite));
                if (site.isPresent()) {
                    if (topo.get().getSites().contains(site.get())){
                        topo.get().getSites().remove(site.get());
                        Topo topoNew = topo.get();
                        topoManagement.delete(topo.get());
                        topoManagement.save(topoNew);
                    }
                }
            }
            return "redirect:/profile";
        } catch (Exception e) {
            return "erreur";
        }
    }

    @GetMapping("/site")
    public String site(Model model, HttpServletRequest request, @RequestParam(value = "errors", required = false) List<String> errors) {
        Site site = new Site();
        site.setCotationMin(0);
        site.setCotationMax(0);
        site.setHauteurMin(0);
        site.setHauteurMax(0);
        site.setDate(new Date(System.currentTimeMillis()));
        Utilisateur utilisateur = utilisateurManagement.findByRequest(request);
        site.setUtilisateur(utilisateur);
        model.addAttribute("utilisateur", utilisateur);
        model.addAttribute("site", site);
        model.addAttribute("errors", errors);
        model.addAttribute("departements", departements);
        return "site_creation";
    }

    @PostMapping("/site/save")
    public String creation_site(@ModelAttribute Site site, Model model, HttpServletRequest request, RedirectAttributes ra) {
        Utilisateur utilisateur = utilisateurManagement.findByRequest(request);
        List<String> errors = validationModel.verifyValidity(site);

        errors.addAll(siteManagement.validDepartement(site));

        if (errors.size() == 0) {
            site.setUtilisateur(utilisateur);
            siteManagement.save(site);
            String object_type = "site";
            model.addAttribute("utilisateur", utilisateur);
            model.addAttribute("site", site);
            model.addAttribute("object_type", object_type);
            return "redirect:/profile";
        }
        ra.addAttribute("errors", errors);
        return "redirect:/site";
    }

    @GetMapping("/site/{id}")
    public String listMesSites(Model model,
                               HttpServletRequest request, @PathVariable String id) {
        try {
            Long long_id = Long.parseLong(id);
            Optional<Site> site = siteManagement.findById(long_id);
            if (site.isPresent()) {
                Commentaire commentaire = new Commentaire();
                commentaire.setUtilisateur(utilisateurManagement.findByRequest(request));
                commentaire.setSite(site.get());

                model.addAttribute("site", site.get());
                model.addAttribute("commentaire_write", commentaire);
                model.addAttribute("utilisateur_show", site.get().getUtilisateur());
                model.addAttribute("commentaires", commentaireManagement.findCommentairesBySiteId(long_id));
                model.addAttribute("redirectionId", id);
                model.addAttribute("cotations", new Cotations());
            }
        } catch (Exception ignored) {
        }
        return "site_show";
    }

    @GetMapping("/site/{id}/edit")
    public String site_edit(Model model, @PathVariable String id) {
        try {
            Optional<Site> site = siteManagement.findById(Long.parseLong(id));
            if (site.isPresent()) {
                model.addAttribute(site.get());
                model.addAttribute("departements", departements);
                return "site_creation";
            }
        } catch (Exception ignored) {
        }
        return "erreur";
    }

    @GetMapping("/site/{id}/delete")
    public String site_delete(@PathVariable String id) {
        Long long_id = Long.parseLong(id);
        Optional<Site> site = siteManagement.findById(long_id);
        if (site.isPresent()) {
            if (site.get().getCommentaires() != null)
                commentaireManagement.deleteCommentairesBySiteId(site.get().getId());
            siteManagement.deleteById(long_id);
        }
        return "redirect:/profile";
    }

    @GetMapping("/site/{id}/secteur")
    public String creation_secteur(Model model, @PathVariable String id, @RequestParam(value = "errors", required = false) List<String> errors) {
        try {
            Secteur secteur = new Secteur();
            secteur.setDate(new Date(System.currentTimeMillis()));
            Optional<Site> site = siteManagement.findById(Long.parseLong(id));
            if (site.isPresent()) {
                secteur.setSite(site.get());
                model.addAttribute("secteur", secteur);
            }
            model.addAttribute("errors", errors);
            return "secteur_creation";
        } catch (Exception ignored) {
        }
        return "erreur";
    }

    @GetMapping("/secteur/{id}")
    public String secteur(Model model, @PathVariable String id) {
        try {
            Optional<Secteur> secteur = secteurManagement.findById(Long.parseLong(id));
            if (secteur.isPresent()) {
                model.addAttribute("secteur", secteur.get());
                model.addAttribute("cotations", new Cotations());
            }
        } catch (Exception ignored) {
        }
        return "secteur_show";
    }

    @PostMapping("/secteur/save")
    public String creation_secteur(@ModelAttribute Secteur secteur, Model model, HttpServletRequest request, RedirectAttributes ra) {
        Utilisateur utilisateur = utilisateurManagement.findByRequest(request);
        List<String> errors = validationModel.verifyValidity(secteur);

        if (errors.size() == 0) {
            Date date = new Date(System.currentTimeMillis());
            secteur.setDate(date);
            secteurManagement.save(secteur);

            String object_type = "secteur";
            model.addAttribute("utilisateur", utilisateur);
            model.addAttribute("secteur", secteur);
            model.addAttribute("object_type", object_type);
            return "redirect:/site/" + secteur.getSite().getId();
        }
        ra.addAttribute("errors", errors);
        return "redirect:/site/" + secteur.getSite().getId() + "/secteur";

    }

    @GetMapping("/secteur/{id}/edit")
    public String secteur_edit(Model model, @PathVariable String id) {
        try {
            Optional<Secteur> secteur = secteurManagement.findById(Long.parseLong(id));
            if (secteur.isPresent()) {
                model.addAttribute(secteur.get());
                return "secteur_creation";
            }
        } catch (Exception ignored) {
        }
        return "erreur";
    }

    @GetMapping("/secteur/{id}/delete")
    public String secteur_delete(@PathVariable String id, HttpServletRequest request) {
        try {
            Long long_id = Long.parseLong(id);
            Utilisateur utilisateur = utilisateurManagement.findByRequest(request);
            Optional<Secteur> secteur = secteurManagement.findById(long_id);
            if (secteur.isPresent()) {
                if (utilisateur.getId().equals(secteur.get().getSite().getUtilisateur().getId())) {
                    secteurManagement.deleteById(long_id);
                }
                return "redirect:/site/" + secteur.get().getSite().getId();
            }
        } catch (Exception ignored) {
        }
        return "redirect:/erreur";
    }

    @GetMapping("/secteur/{id}/voie")
    public String creation_voie(Model model, @PathVariable String id, @RequestParam(value = "errors", required = false) List<String> errors) {
        try {
            Voie voie = new Voie();
            voie.setDate(new Date(System.currentTimeMillis()));
            voie.setSecteur(secteurManagement.findSecteurById(Long.parseLong(id)));
            model.addAttribute("voie", voie);
            model.addAttribute("cotations", cotations);
            model.addAttribute("errors", errors);
            return "voie_creation";
        } catch (Exception ignored) {
        }
        return "erreur";
    }

    @GetMapping("/voie/{id}")
    public String voie(Model model, @PathVariable String id) {
        try {
            Optional<Voie> voie = voieManagement.findById(Long.parseLong(id));
            if (voie.isPresent()) {
                model.addAttribute("voie", voie.get());
                model.addAttribute("cotations", new Cotations());
            }
        } catch (Exception ignored) {
        }
        return "voie_show";
    }

    @PostMapping("/voie/save")
    public String creation_voie(@ModelAttribute Voie voie, Model model, HttpServletRequest request, RedirectAttributes ra) {
        List<String> errors = validationModel.verifyValidity(voie);
        String redirect = "redirect:/secteur/" + voie.getSecteur().getId() + "/voie";

        if (errors.size() == 0) {
            Utilisateur utilisateur = utilisateurManagement.findByRequest(request);
            Date date = new Date(System.currentTimeMillis());
            voie.setDate(date);
            voieManagement.save(voie);

            String object_type = "voie";
            model.addAttribute("utilisateur", utilisateur);
            model.addAttribute("voie", voie);
            model.addAttribute("object_type", object_type);
            return "redirect:/secteur/" + voie.getSecteur().getId();
        }
        ra.addAttribute("errors", errors);
        return redirect;
    }

    @GetMapping("/voie/{id}/edit")
    public String voie_edit(Model model, @PathVariable String id) {
        try {
            Optional<Voie> voie = voieManagement.findById(Long.parseLong(id));
            if (voie.isPresent()) {
                model.addAttribute(voie.get());
                return "voie_creation";
            }
        } catch (Exception ignored) {
        }
        return "erreur";
    }

    @GetMapping("/voie/{id}/delete")
    public String voie_delete(@PathVariable String id) {
        try {
            Optional<Voie> voie = voieManagement.findById(Long.parseLong(id));
            if (voie.isPresent()) {
                voieManagement.deleteById(Long.parseLong(id));
            }
        } catch (Exception ignored) {
        }
        return "redirect:/profile";
    }

    @PostMapping("/comment/{id}")
    public String comment_add(@ModelAttribute Commentaire commentaire, @PathVariable String id) {
        try {
            commentaire.setDate(new Date(System.currentTimeMillis()));
            commentaireManagement.save(commentaire);
        } catch (Exception ignored) {
        }

        String redirection = "redirect:/";
        if (commentaire.getSite() != null) {
            redirection += "site";
        } else if (commentaire.getTopo() != null) {
            redirection += "topo";
        } else {
            redirection += "error";
        }
        redirection += "/";
        redirection += id;
        return redirection;
    }

    @PostMapping("/comment/{id}/delete")
    public String comment_delete(@PathVariable String id, HttpServletRequest request) {
        try {
            Long long_id = Long.parseLong(id);
            String id_redirect = "";
            String redirection = "redirect:/";
            Optional<Commentaire> commentaire = commentaireManagement.findById(long_id);
            if (commentaire.isPresent()) {
                if (commentaire.get().getSite() != null) {
                    redirection += "site";
                    id_redirect = Long.toString(commentaire.get().getSite().getId());
                } else if (commentaire.get().getTopo() != null) {
                    redirection += "topo";
                    id_redirect = Long.toString(commentaire.get().getTopo().getId());
                } else {
                    redirection += "error";
                }
                redirection += "/";
                redirection += id_redirect;

                Utilisateur utilisateur = utilisateurManagement.findByRequest(request);
                if (commentaire.get().getUtilisateur().getId().equals(utilisateur.getId()))
                    commentaireManagement.deleteById(long_id);
                return redirection;
            }
        } catch (Exception ignored) {
        }
        return "erreur";
    }
}
