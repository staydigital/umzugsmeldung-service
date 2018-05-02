package de.nkk.oasis.tools.othworkshop.umzugmeldung;

import de.nkk.oasis.tools.othworkshop.umzugmeldung.domain.Adresse;
import de.nkk.oasis.tools.othworkshop.umzugmeldung.domain.VN;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

@Configuration
public class RepositoryConfig extends RepositoryRestConfigurerAdapter {

	@Override public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		config.exposeIdsFor(Adresse.class);
		config.exposeIdsFor(VN.class);
	}
}