package fr.fabien.escalade.business;

import fr.fabien.escalade.consumer.utilisateur.UtilisateurRepository;
import fr.fabien.escalade.model.topo.Utilisateur;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class UtilisateurManagement {
    @Autowired
    private final UtilisateurRepository repository;

    public void inscription(Utilisateur utilisateur) {
        Utilisateur testUtilisateur = repository.findFirstByCourrielOrLogin(
                utilisateur.getCourriel(),
                utilisateur.getLogin()
        );

        if (testUtilisateur != null) {
            System.out.println("Erreur - Ces identifiants existent déjà, id = " + testUtilisateur.getId());
        } else {
            repository.save(utilisateur);
        }
    }
}
