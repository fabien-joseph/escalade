package fr.fabien.escalade.business;

import fr.fabien.escalade.consumer.topo.SiteRepository;
import fr.fabien.escalade.model.topo.Site;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static fr.fabien.escalade.business.Departements.departements;

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

    public List<String> validDepartement(Site site) {
        List<String> errors = new ArrayList<>();
        if (site.getDepartement() != null) {
            for (Map.Entry<String, String> entry : departements.entrySet()) {
                if (site.getDepartement().equals(entry.getKey()))
                    return errors;
            }
        }
        errors.add("Le département indiqué n'est pas valide.");
        return errors;
    }

    public List<Site> findSitesAdvanced(Integer hauteurMin, Integer hauteurMax,
                                        Integer cotationMin, Integer cotationMax,
                                        String nom, String departement) {
        Site site = new Site();
        site.setNom(nom);
        site.setDepartement(departement);
        List<Site> sites = repository.findSitesAdvanced(site.getNom(), site.getDepartement());
        if (hauteurMin == null)
            hauteurMin = 0;
        if (hauteurMax == null)
            hauteurMax = 9999;
        if (cotationMin == null)
            cotationMin = 0;
        if (cotationMax == null)
            cotationMax = 999;

        for (int i = 0; i < sites.size(); i++) {
            if (sites.get(i).getCotationMin() == null)
                sites.get(i).setCotationMin(-1);
            if (sites.get(i).getCotationMax() == null)
                sites.get(i).setCotationMax(-1);
            if (sites.get(i).getHauteurMin() == null)
                sites.get(i).setHauteurMin(-1);
            if (sites.get(i).getHauteurMax() == null)
                sites.get(i).setHauteurMax(-1);

            System.out.println(sites.get(i).getId() + " : POUR NOM : " + sites.get(i).getNom() +
                    " hauteur " + sites.get(i).getHauteurMin() + "/" + sites.get(i).getHauteurMax() +
                    " cotation " + sites.get(i).getCotationMin() + "/" + sites.get(i).getCotationMax());

            if (!((sites.get(i).getCotationMin() >= cotationMin && sites.get(i).getCotationMax() <= cotationMax) &&
                    (sites.get(i).getHauteurMin() >= hauteurMin && sites.get(i).getHauteurMax() <= hauteurMax))) {
                sites.remove(i);
                i--;
            }
        }
        System.out.println("==========");
        return sites;
    }

    public Site findSiteByNom(String nom) {
        return repository.findSiteByNom(nom);
    }

    public List<Site> findSitesByTopo_id(Long topoId) {
        return repository.findSitesByTopo_id(topoId);
    }

    public List<Site> findAllByTopos_Id(Long id) {
        return repository.findAllByTopos_Id(id);
    }

    public List<Site> findSitesByDepartement(String departement) {
        return repository.findSitesByDepartement(departement);
    }

    /*
    public void updateMinMax(Site site) {
        Integer minValue = 0;
        Integer maxValue = 0;

        for (int i = 0; i < site.getSecteurs().size(); i++) {
            if (site.getSecteurs().get(i).getHauteurMin() != null && site.getSecteurs().get(i).getHauteurMax() != null) {
                if (i == 0) {
                    if (site.getSecteurs().get(i).getHauteurMin() != null)
                        minValue = site.getSecteurs().get(i).getHauteurMin();
                    if (site.getSecteurs().get(i).getHauteurMax() != null)
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
        }
        site.setHauteurMin(minValue);
        site.setHauteurMax(maxValue);

        minValue = 0;
        maxValue = 0;

        for (int i = 0; i < site.getSecteurs().size(); i++) {
            if (site.getSecteurs().get(i).getCotationMin() != null && site.getSecteurs().get(i).getCotationMax() != null) {
                if (i == 0) {
                    if(site.getSecteurs().get(i).getCotationMin() != null)
                        minValue = site.getSecteurs().get(i).getCotationMin();
                    if(site.getSecteurs().get(i).getCotationMax() != null)
                        maxValue = site.getSecteurs().get(i).getCotationMin();
                } else {
                    if (site.getSecteurs().get(i).getCotationMin() < minValue) {
                        minValue = site.getSecteurs().get(i).getCotationMin();
                    }
                    if (site.getSecteurs().get(i).getCotationMax() > maxValue) {
                        maxValue = site.getSecteurs().get(i).getCotationMax();
                    }

                }
            }
        }
        site.setCotationMin(minValue);
        site.setCotationMax(maxValue);

        save(site);
    }
     */
}
