package fr.fabien.escalade.business;

import fr.fabien.escalade.consumer.topo.SiteRepository;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {
    @Bean
    public SiteManagement siteManagement(SiteRepository siteRepository) {
        return new SiteManagement(siteRepository);
    }
}
