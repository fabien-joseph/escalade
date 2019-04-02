package fr.fabien.escalade.business;

import fr.fabien.escalade.consumer.utilisateur.UtilisateurRepository;
import fr.fabien.escalade.model.topo.Utilisateur;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
public class UtilisateurManagement implements UserDetailsService {
    @Autowired
    private final UtilisateurRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utilisateur utilisateur = repository.findByLogin(username);
        if (utilisateur == null)
            throw new UsernameNotFoundException("User not found... 404");

        return new UtilisateurPrincipal(utilisateur);
    }
    public void inscription(Utilisateur utilisateur) {
        Utilisateur testUtilisateur = repository.findFirstByCourrielOrLogin(
                utilisateur.getCourriel(),
                utilisateur.getLogin()
        );

        if (testUtilisateur != null) {
            System.out.println("Erreur - Ces identifiants existent déjà, id = " + testUtilisateur.getId());
        } else {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            utilisateur.setMotDePasse(bCryptPasswordEncoder.encode(utilisateur.getMotDePasse()));
            repository.save(utilisateur);
        }
    }

    public Utilisateur findByLogin (String login) {
        return repository.findByLogin(login);
    }

    public Utilisateur findById (Long id) {
        Optional<Utilisateur> utilisateurOptional = repository.findById(id);
        Utilisateur utilisateur = null;
        if(utilisateurOptional.isPresent())
            utilisateur = utilisateurOptional.get();
        return utilisateur;
    }

}
