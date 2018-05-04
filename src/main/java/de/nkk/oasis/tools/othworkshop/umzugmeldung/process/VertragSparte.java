package de.nkk.oasis.tools.othworkshop.umzugmeldung.process;

import de.nkk.oasis.tools.othworkshop.umzugmeldung.domain.Vertrag;
import org.springframework.stereotype.Component;

@Component
public class VertragSparte {

	public String getSparte(Vertrag vertrag) {
		if (vertrag.getHausrat() != null) {
			return "Hausratversicherung";
		}
		if (vertrag.getLV() != null) {
			return "Lebensversicherung";
		}
		if (vertrag.getHaftpflicht() != null) {
			return "Haftpflichtversicherung";
		}
		if (vertrag.getKfz() != null) {
			return "Kfzversicherung";
		}
		if (vertrag.getUnfall() != null) {
			return "Unfallversicherung";
		}
		return "";
	}
}
