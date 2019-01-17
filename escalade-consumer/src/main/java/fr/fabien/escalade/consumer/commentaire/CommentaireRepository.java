package fr.fabien.escalade.consumer.commentaire;

import fr.fabien.escalade.model.commentaire.Commentaire;
import org.springframework.data.repository.CrudRepository;

public interface CommentaireRepository extends CrudRepository<Commentaire, Long> {
}
