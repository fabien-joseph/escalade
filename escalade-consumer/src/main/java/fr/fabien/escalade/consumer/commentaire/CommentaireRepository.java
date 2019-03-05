package fr.fabien.escalade.consumer.commentaire;

import fr.fabien.escalade.model.topo.Commentaire;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentaireRepository extends CrudRepository<Commentaire, Long> {
    List<Commentaire> findCommentairesByUtilisateur_id(Long utilisateur_id);
}
