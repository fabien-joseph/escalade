package fr.fabien.escalade.consumer.commentaire;

import fr.fabien.escalade.model.commentaire.Commentaire;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentaireRepository extends CrudRepository<Commentaire, Long> {
    List<Commentaire> findCommentairesByUtilisateur_id(Long utilisateur_id);
}
