package fr.fabien.escalade.consumer.topo;

import fr.fabien.escalade.model.topo.Site;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SiteRepository extends CrudRepository<Site, Long> {
    List<Site> findSitesByTopo_id(Long topo_id);

    List<Site> findSitesByUtilisateurId(Long id);

    List<Site> findSitesByNomContainingIgnoreCase(String nom);
}
