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
		createVN("Huber", "Michael", "HM200078001", createAdresse("Böhmerwaldstraße 13", "93057", "Regensburg", "Deutschland"),
				Arrays.asList(createHausrat("HMHS001", true, 120),
						createLVVertrag("HMLV001", 150000L),
						createHaftpflicht("HMHF001", 75000L),
						createKfz("HMKFZ001", "R-A 923", true),
						createUnfallversicherung("HMUNF001", 98000L)));
		createVN("Flieder", "Anton", "FA200078002", createAdresse("Lupburger Str. 24", "92331", "Parsberg", "Deutschland"),
				Arrays.asList(createHausrat("FAHS001", true, 120),
						createLVVertrag("FALV001", 150000L),
						createHaftpflicht("FAHF001", 75000L),
						createKfz("FAKFZ001", "P-X 528", true),
						createUnfallversicherung("FAUNF001", 98000L)));
		createVN("Pressl", "Veronika", "PV200078002", createAdresse("Ritter-Haug-Str. 2", "92331", "Parsberg", "Deutschland"),
				Arrays.asList(createHausrat("PVHS001", true, 120),
						createLVVertrag("PVLV001", 150000L),
						createHaftpflicht("PVHF001", 75000L),
						createKfz("PVKFZ001", "P-AC 1024", true),
						createUnfallversicherung("PVUNF001", 98000L)));
		createVN("Lößnitz", "Marita", "LM200078002", createAdresse("Riesengebirgsstr. 56", "93073", "Neutraubling", "Deutschland"),
				Arrays.asList(createHausrat("LMHS001", true, 120),
						createLVVertrag("LMLV001", 150000L),
						createHaftpflicht("LMHF001", 75000L),
						createKfz("LMKFZ001", "R-JK 343", true),
						createUnfallversicherung("LMUNF001", 98000L)));
		createVN("Schwarzer", "Thorsten", "ST200078002", createAdresse("Pfarrer-Graf-Str. 4", "93133", "Burglengenfeld", "Deutschland"),
				Arrays.asList(createHausrat("STHS001", true, 120),
						createLVVertrag("STLV001", 150000L),
						createHaftpflicht("STHF001", 75000L),
						createKfz("STKFZ001", "R-JK 343", true),
						createUnfallversicherung("STUNF001", 98000L)));

	}

	@Override public void afterPropertiesSet() throws Exception {
		doInitialData();
	}
}
