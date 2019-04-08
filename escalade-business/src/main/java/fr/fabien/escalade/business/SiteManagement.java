package fr.fabien.escalade.business;

import fr.fabien.escalade.consumer.topo.SiteRepository;
import fr.fabien.escalade.model.topo.Site;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class SiteManagement {
    private final SiteRepository repository;

    public List<Site> findSitesByTopo_id(Long id) {
        return repository.findSitesByTopo_id(id);
    }

    public List<Site> findSitesByUtilisateurId(Long id) {
        return repository.findSitesByUtilisateurId(id);
    }

    public Site findSiteById (Long id) {
        return repository.findSiteById(id);
    }

    public void ajout(Site site) {
        Site testSite = repository.findFirstByNom(
                site.getNom()
        );

        if (testSite != null) {
            System.out.println("Erreur - Ce site existe déjà, id = " + testSite.getId());
        } else {
            repository.save(site);
        }
    }
}
