package fr.fabien.escalade.business.topo;

import fr.fabien.escalade.consumer.topo.VoieRepository;
import fr.fabien.escalade.model.topo.Voie;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class VoieManagement {
    private final VoieRepository repository;

    public Iterable<Voie> findAll() {
        return repository.findAll();
    }
}
