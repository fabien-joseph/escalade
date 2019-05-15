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
public class SiteManagement extends CrudManager<Site, SiteRepository> {

    public SiteManagement(SiteRepository repository) {
        super(repository);
    }

    public List<Site> findSitesByUtilisateurId(Long id) {
        return repository.findSitesByUtilisateurId(id);
    }

    public List<Site> findSitesByNom(String nom) {
        return repository.findSitesByNomContainingIgnoreCase(nom);
    }

    public List<Site> findSitesAdvanced(int hauteurMin, int hauteurMax, String nom, String departement) {
        return repository.findSitesAdvanced(hauteurMin, hauteurMax, nom, departement);
    }

    public Site findSiteByNom(String nom) {
        return repository.findSiteByNom(nom);
    }

    public void updateMinMax(Site site) {
        Integer minValue = null;
        Integer maxValue = null;

        for (int i = 0; i < site.getSecteurs().size(); i++) {
            if (i == 0) {
                minValue = site.getSecteurs().get(i).getHauteurMin();
                maxValue = site.getSecteurs().get(i).getHauteurMax();
            } else {
                if (site.getSecteurs().get(i).getHauteurMin() < minValue) {
                    minValue = site.getSecteurs().get(i).getHauteurMin();
                }
                if (site.getSecteurs().get(i).getHauteurMax() > maxValue) {
                    maxValue = site.getSecteurs().get(i).getHauteurMax();
                }

            }
        }

        site.setHauteurMin(minValue);
        site.setHauteurMax(maxValue);
        save(site);
    }
}
