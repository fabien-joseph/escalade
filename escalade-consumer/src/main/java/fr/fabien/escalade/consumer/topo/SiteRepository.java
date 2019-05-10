package fr.fabien.escalade.consumer.topo;

import fr.fabien.escalade.model.topo.Site;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SiteRepository extends CrudRepository<Site, Long> {
    List<Site> findSitesByUtilisateurId(Long id);

    List<Site> findSitesByNomContainingIgnoreCase(String nom);

    Site findSiteByNom(String nom);

    @Query("SELECT s FROM Site s WHERE s.hauteurMin >= :hauteurMin AND s.hauteurMax <= :hauteurMax AND lower(s.nom) " +
            "like %:nom% AND (s.departement = :departement OR '' = ?4)")
    List<Site> findSitesAdvanced(@Param("hauteurMin") int hauteurMin, @Param("hauteurMax") int hauteurMax,
                                 @Param("nom") String nom, @Param("departement") String departement);
}
