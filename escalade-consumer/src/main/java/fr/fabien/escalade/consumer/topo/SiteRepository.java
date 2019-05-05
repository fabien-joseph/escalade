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

    @Query("SELECT s FROM Site s WHERE s.hauteurMin >= ?1 AND s.hauteurMax <= ?2 AND lower(s.nom) like %?3% AND (s.departement = ?4 OR '' = ?4)")
    List<Site> findSitesAdvanced(int hauteurMin, int hauteurMax, String nom, String departement);
}
