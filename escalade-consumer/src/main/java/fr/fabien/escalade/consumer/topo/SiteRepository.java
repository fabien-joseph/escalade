package fr.fabien.escalade.consumer.topo;

import fr.fabien.escalade.model.topo.Site;
import fr.fabien.escalade.model.topo.Utilisateur;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SiteRepository extends CrudRepository<Site, Long> {
    List<Site> findSitesByUtilisateurId(Long id);

    List<Site> findSitesByDepartement(String departement);

    List<Site> findSitesByNomContainingIgnoreCase(String nom);

    Site findSiteByNom(String nom);

    @Query("SELECT s FROM Site s WHERE " +
            "s.hauteurMin >= :hauteurMin AND s.hauteurMax <= :hauteurMax " +
            "AND s.cotationMin >= :cotationMin AND s.cotationMax <= :cotationMax " +
            "AND lower(s.nom) like %:nom% AND (s.departement = :departement OR '' = :departement)")
    List<Site> findSitesAdvanced(@Param("hauteurMin") int hauteurMin, @Param("hauteurMax") int hauteurMax,
                                 @Param("cotationMin") int cotationMin, @Param("cotationMax") int cotationMax,
                                 @Param("nom") String nom, @Param("departement") String departement);

    @Query(value = "SELECT s FROM Topo t join site_topo ON t.id = site_topo.topo_id join Site s ON s.id = site_topo.site_id WHERE t.id = ?1", nativeQuery = true)
    List<Site> findSitesByTopo_id(Long topoId);

    List<Site> findAllByTopos_Id(Long id);
}
