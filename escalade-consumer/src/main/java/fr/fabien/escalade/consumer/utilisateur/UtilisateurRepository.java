package fr.fabien.escalade.consumer.utilisateur;

import fr.fabien.escalade.model.topo.Utilisateur;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurRepository extends CrudRepository<Utilisateur, Long> {
        Utilisateur findFirstByCourrielOrLogin(String courriel, String login);

        Utilisateur findByLogin(String login);

        Optional<Utilisateur> findById(Long id);
}
