package fr.fabien.escalade.business.topo;

import fr.fabien.escalade.consumer.topo.SecteurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class SecteurManagement {
    private final SecteurRepository repository;
}
