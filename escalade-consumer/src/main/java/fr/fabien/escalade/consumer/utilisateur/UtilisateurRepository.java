package fr.fabien.escalade.consumer.utilisateur;

import fr.fabien.escalade.model.topo.Utilisateur;
import org.springframework.data.repository.CrudRepository;

public interface UtilisateurRepository extends CrudRepository<Utilisateur, Long> {

}
