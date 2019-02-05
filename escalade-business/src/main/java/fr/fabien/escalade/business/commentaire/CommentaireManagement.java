package fr.fabien.escalade.business.commentaire;

import fr.fabien.escalade.consumer.commentaire.CommentaireRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class CommentaireManagement {
    private final CommentaireRepository repository;
}
