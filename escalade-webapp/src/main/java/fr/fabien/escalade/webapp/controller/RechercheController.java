package fr.fabien.escalade.webapp.controller;

import fr.fabien.escalade.business.*;
import fr.fabien.escalade.model.topo.Commentaire;
import fr.fabien.escalade.model.topo.Site;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class RechercheController {
    @Autowired
    TopoManagement topoManagement;
    @Autowired
    CommentaireManagement voieManagement;
    @Autowired
    SiteManagement siteManagement;
    @Autowired
    UtilisateurManagement utilisateurManagement;

    @RequestMapping(value = "/recherche")
    @ResponseBody
    public List<String> listTopos(@RequestParam(value = "term", required = false, defaultValue = "") String term) {

        return siteManagement.findSitesByNom(term)
                .stream()
                .map(Site::getNom)
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/test")
    @ResponseBody
    public Commentaire test() {
        Optional<Commentaire> commentaire = voieManagement.findById(1L);
        return commentaire.get();
    }
}
