package fr.fabien.escalade.business.topo;

import fr.fabien.escalade.consumer.topo.SiteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class SiteManagement {
    private final SiteRepository repository;
}
