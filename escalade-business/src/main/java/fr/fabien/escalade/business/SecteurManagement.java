package fr.fabien.escalade.business;

import fr.fabien.escalade.consumer.topo.SecteurRepository;
import fr.fabien.escalade.model.topo.Secteur;
import fr.fabien.escalade.model.topo.Site;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class SecteurManagement {
    private final SecteurRepository repository;

    public List<Secteur> findSecteursBySite_id (Long id) {
        return repository.findSecteursBySite_id(id);
    }

    public void ajout (Secteur secteur) {
        Secteur testSecteur = repository.findFirstByNom(
                secteur.getNom()
        );

        if (testSecteur != null) {
            System.out.println("Erreur - Ce secteur existe déjà, id = " + testSecteur.getId());
        } else {
            repository.save(secteur);
        }
    }

    public Secteur findSecteurById(Long id) {
        return repository.findSecteurById(id);
    }

}
