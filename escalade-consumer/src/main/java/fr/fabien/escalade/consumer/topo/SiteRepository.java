package fr.fabien.escalade.consumer.topo;

import fr.fabien.escalade.model.topo.Site;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface SiteRepository extends CrudRepository<Site, Long> {
    List<Site> findSitesByTopo_id(Long topo_id);
}
