package fr.fabien.escalade.business;

import fr.fabien.escalade.consumer.topo.SecteurRepository;
import fr.fabien.escalade.model.topo.Secteur;
import fr.fabien.escalade.model.topo.Site;
import fr.fabien.escalade.model.topo.Voie;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class SecteurManagement extends CrudManager<Secteur, SecteurRepository> {
    @Autowired
    SiteManagement siteManagement;

    public SecteurManagement(SecteurRepository repository) {
        super(repository);
    }

    public List<Secteur> findSecteursBySite_id(Long id) {
        return repository.findSecteursBySite_id(id);
    }

    public Secteur findSecteurById(Long id) {
        return repository.findSecteurById(id);
    }

    public void updateMinMax(Secteur secteur) {
        Integer minValue = null;
        Integer maxValue = null;

        for (int i = 0; i < secteur.getVoies().size(); i++) {
            if (i == 0) {
                minValue = secteur.getVoies().get(i).getLongueur();
                maxValue = secteur.getVoies().get(i).getLongueur();
            } else {
                if (secteur.getVoies().get(i).getLongueur() < minValue) {
                    minValue = secteur.getVoies().get(i).getLongueur();
                }
                if (secteur.getVoies().get(i).getLongueur() > maxValue) {
                    maxValue = secteur.getVoies().get(i).getLongueur();
                }

            }
        }

        secteur.setHauteurMin(minValue);
        secteur.setHauteurMax(maxValue);

        Site site = secteur.getSite();
        site.getSecteurs().add(secteur);
        siteManagement.save(site);
        siteManagement.updateMinMax(site);
    }
}
