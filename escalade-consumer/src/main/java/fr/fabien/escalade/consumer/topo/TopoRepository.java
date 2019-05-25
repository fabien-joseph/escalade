package fr.fabien.escalade.consumer.topo;

import fr.fabien.escalade.model.topo.Topo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopoRepository extends CrudRepository<Topo, Long> {
    List<Topo> findToposByUtilisateur_id(Long utilisateur_id);

    Topo findTopoById (Long id);

    List<Topo> findAll();

    Topo findFirstByNom (String nom);

    @Query(value = "DELETE FROM site_topo s WHERE s.topo_id = :topoId", nativeQuery = true)
    boolean deleteAllInSite_topo(@Param("topoId") Long topoId);

    List<Topo> findAllBySites_Id (Long siteId);
}