package fr.fabien.escalade.consumer.commentaire;

import fr.fabien.escalade.model.topo.Commentaire;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentaireRepository extends CrudRepository<Commentaire, Long> {
    List<Commentaire> findCommentairesByUtilisateur_id(Long utilisateur_id);
    List<Commentaire> findCommentairesBySiteId(Long id);
    List<Commentaire> findCommentairesByTopoId(Long id);
}
