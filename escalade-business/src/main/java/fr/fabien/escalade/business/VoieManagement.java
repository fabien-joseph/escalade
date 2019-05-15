package fr.fabien.escalade.business;

import fr.fabien.escalade.consumer.topo.VoieRepository;
import fr.fabien.escalade.model.topo.Secteur;
import fr.fabien.escalade.model.topo.Voie;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class VoieManagement extends CrudManager<Voie, VoieRepository> {
    @Autowired
    SecteurManagement secteurManagement;

    public VoieManagement(VoieRepository repository) {
        super(repository);
    }

    public Iterable<Voie> findAll() {
        return repository.findAll();
    }

    public Voie findVoieById(Long id) {
        return repository.findVoieById(id);
    }

    @Override
    public void save(Voie voie) {
        Secteur secteur = voie.getSecteur();
        secteur.getVoies().add(voie);
        secteurManagement.save(secteur);
        secteurManagement.updateMinMax(secteur);
    }
}
