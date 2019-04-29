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

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Optional;

@Transactional
@Service
public class UtilisateurManagement extends CrudManager<Utilisateur, UtilisateurRepository> implements UserDetailsService {

    public UtilisateurManagement(UtilisateurRepository repository) {
        super(repository);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utilisateur utilisateur = repository.findByLogin(username);
        if (utilisateur == null)
            throw new UsernameNotFoundException("User not found... 404");

        return new UtilisateurPrincipal(utilisateur);
    }

    public Utilisateur findByLogin(String login) {
        return repository.findByLogin(login);
    }

    public Utilisateur findByRequest(HttpServletRequest request) {
        Utilisateur utilisateur = null;
        if (request.getUserPrincipal() != null)
            utilisateur = findByLogin(request.getUserPrincipal().getName());
        return utilisateur;
    }

    @Override
    public void save(Utilisateur utilisateur) {
        Date date = new Date(System.currentTimeMillis());
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        utilisateur.setDate(date);
        utilisateur.setMotDePasse(bCryptPasswordEncoder.encode(utilisateur.getMotDePasse()));
        repository.save(utilisateur);
    }
}
