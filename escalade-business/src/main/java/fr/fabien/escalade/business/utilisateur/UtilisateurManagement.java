package fr.fabien.escalade.business.utilisateur;

import fr.fabien.escalade.consumer.utilisateur.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class UtilisateurManagement {
    private final UtilisateurRepository repository;
}
