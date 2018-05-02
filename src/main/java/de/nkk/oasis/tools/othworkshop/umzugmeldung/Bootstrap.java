package de.nkk.oasis.tools.othworkshop.umzugmeldung;

import de.nkk.oasis.tools.othworkshop.umzugmeldung.domain.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class Bootstrap implements InitializingBean {

	@Autowired
	private VNRepository vnRepository;

	private VN createVN(String vorname, String nachname, String kundennummer, Adresse addresse, List<Vertrag> vertraege) {
		VN vn = new VN();
		vn.setVorname(vorname);
		vn.setNachname(nachname);
		vn.setKundennummer(kundennummer);
		vn.setAdresse(addresse);

		vn.setVertraege(vertraege);
		for (Vertrag vertrag : vertraege) {
			vertrag.setAdresse(addresse);
		}
		vnRepository.save(vn);

		return vn;
	}
	private Adresse createAdresse(String strasse, String plz, String wohnort, String land) {
		Adresse addr = new Adresse();
		addr.setStrasse(strasse);
		addr.setPlz(plz);
		addr.setOrt(wohnort);
		addr.setLand(land);

		return addr;
	}

	static Vertrag createLVVertrag(String vsnr, String lVProp) {
		Vertrag v = new Vertrag();
		VertragLV vertrag = new VertragLV();
		v.setVsnr(vsnr);
		v.setLV(vertrag);
		vertrag.setLVProp(lVProp);
		return v;
	}

	private void doInitialData() {
		createVN("Stefan", "Wittmann", "X001", createAdresse("Böhmerwaldstraße 13", "93057", "Regensburg", "Deutschland"), Arrays.asList(createLVVertrag("XYZ001", "1"), createLVVertrag("ABC001", "2")));
		createVN("Max", "Mustermann", "Y001", createAdresse("Musterweg 25", "12345", "Musterstadt", "Deutschland"), Arrays.asList(createLVVertrag("JDK084", "1"), createLVVertrag("UO123", "2")));
	}

	@Override public void afterPropertiesSet() throws Exception {
		doInitialData();
	}
}
