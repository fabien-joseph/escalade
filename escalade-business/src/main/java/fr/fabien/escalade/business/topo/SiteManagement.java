package fr.fabien.escalade.business.topo;

import fr.fabien.escalade.consumer.topo.SiteRepository;
import fr.fabien.escalade.model.topo.Site;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class SiteManagement {
    private final SiteRepository repository;

    public List<Site> findSitesByTopo_id (Long id) {
        return repository.findSitesByTopo_id(id);
    }
}
