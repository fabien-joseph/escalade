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

    public List<Site> findSitesAdvanced(Integer hauteurMin, Integer hauteurMax, String nom, String departement) {
        Site site = new Site();
        site.setNom(nom);
        site.setDepartement(departement);
        site.setHauteurMin(hauteurMin);
        site.setHauteurMax(hauteurMax);
        if (site.getHauteurMin() == null)
            site.setHauteurMin(0);
        if (site.getHauteurMax() == null)
            site.setHauteurMax(9999);
        if (site.getNom() == null)
            site.setNom("");

        System.out.println("Valeur min " + site.getHauteurMin());
        System.out.println("Valeur max " + site.getHauteurMax());
        System.out.println("Nom " + site.getNom());
        System.out.println("Département " + site.getDepartement());

        return repository.findSitesAdvanced(site.getHauteurMin(), site.getHauteurMax(), site.getNom(), site.getDepartement());
    }

    public Site findSiteByNom(String nom) {
        return repository.findSiteByNom(nom);
    }

    public List<Site> findSitesByTopo_id(Long topoId) {
        return repository.findSitesByTopo_id(topoId);
    }

    public List<Site> findSitesByDepartement(String departement) {
        return repository.findSitesByDepartement(departement);
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
