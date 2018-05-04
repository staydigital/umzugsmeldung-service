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

	static Vertrag createHausrat(String vsnr, boolean glasversichert, int quadratmeter) {
		Vertrag v = new Vertrag();
		VertragHausrat vertrag = new VertragHausrat();
		v.setVsnr(vsnr);
		v.setHausrat(vertrag);
		vertrag.setGlasVersichert(glasversichert);
		vertrag.setQuadratMeter(quadratmeter);
		return v;
	}

	static Vertrag createLVVertrag(String vsnr, Long deckungssumme) {
		Vertrag v = new Vertrag();
		VertragLV vertrag = new VertragLV();
		v.setVsnr(vsnr);
		v.setLV(vertrag);
		vertrag.setDeckungsSummeLV(deckungssumme);
		return v;
	}

	static Vertrag createHaftpflicht(String vsnr, Long deckungssumme) {
		Vertrag v = new Vertrag();
		VertragHaftpflicht vertrag = new VertragHaftpflicht();
		v.setVsnr(vsnr);
		v.setHaftpflicht(vertrag);
		vertrag.setDeckungsSummeHaftpflicht(deckungssumme);
		return v;
	}

	static Vertrag createKfz(String vsnr, String kennzeichen, boolean tiefgarageVorhanden) {
		Vertrag v = new Vertrag();
		VertragKfz vertrag = new VertragKfz();
		v.setVsnr(vsnr);
		v.setKfz(vertrag);
		vertrag.setKennzeichen(kennzeichen);
		vertrag.setTiefgarageVorhanden(tiefgarageVorhanden);
		return v;
	}

	static Vertrag createUnfallversicherung(String vsnr, Long deckungssumme) {
		Vertrag v = new Vertrag();
		VertragUnfall vertrag = new VertragUnfall();
		v.setVsnr(vsnr);
		v.setUnfall(vertrag);
		vertrag.setDeckungsSummeUnfall(deckungssumme);
		return v;
	}

	private void doInitialData() {
		createVN("Stefan", "Wittmann", "X001", createAdresse("Böhmerwaldstraße 13", "93057", "Regensburg", "Deutschland"),
				Arrays.asList(createHausrat("WS001", true, 120),
						createLVVertrag("WS002", 150000L),
						createHaftpflicht("WS003", 75000L),
						createKfz("WS004", "R-A 923", true),
						createUnfallversicherung("WS005", 98000L)));
		createVN("Max", "Mustermann", "Y001", createAdresse("Musterweg 25", "12345", "Musterstadt", "Deutschland"), Arrays.asList(createLVVertrag("JDK084", 120000L)));
	}

	@Override public void afterPropertiesSet() throws Exception {
		doInitialData();
	}
}
