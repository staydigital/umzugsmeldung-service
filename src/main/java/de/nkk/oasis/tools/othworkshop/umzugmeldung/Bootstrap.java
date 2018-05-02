package de.nkk.oasis.tools.othworkshop.umzugmeldung;

import de.nkk.oasis.tools.othworkshop.umzugmeldung.domain.Adresse;
import de.nkk.oasis.tools.othworkshop.umzugmeldung.domain.AdresseRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Bootstrap implements InitializingBean {

	@Autowired
	private AdresseRepository adresseRepository;

	private Long createAdresse(String strasse, String plz, String wohnort, String land) {
		Adresse addr = new Adresse();
		addr.setStrasse(strasse);
		addr.setPlz(plz);
		addr.setWohnort(wohnort);
		addr.setLand(land);
		adresseRepository.save(addr);

		return addr.getId();
	}
	private void doInitialData() {
		createAdresse("Böhmerwaldstraße 13", "93057", "Regensburg", "Deutschland");
		createAdresse("Musterweg 25", "12345", "Musterstadt", "Deutschland");
	}

	@Override public void afterPropertiesSet() throws Exception {
		doInitialData();
	}
}
