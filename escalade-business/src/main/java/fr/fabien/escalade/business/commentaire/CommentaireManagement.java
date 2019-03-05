package fr.fabien.escalade.business.commentaire;

import fr.fabien.escalade.consumer.commentaire.CommentaireRepository;
import fr.fabien.escalade.model.topo.Commentaire;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
public class CommentaireManagement {
    @Autowired private final CommentaireRepository repository;

    public Optional<Commentaire> findById(Long id) {
        return repository.findById(id);
    }

    public Iterable<Commentaire> findAll() {
        return repository.findAll();
    }

    public List<Commentaire> findCommentairesByUtilisateur_id (Long id) {
        return repository.findCommentairesByUtilisateur_id(id);
    }
}
