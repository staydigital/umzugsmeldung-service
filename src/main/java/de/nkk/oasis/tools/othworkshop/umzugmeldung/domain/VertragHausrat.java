package de.nkk.oasis.tools.othworkshop.umzugmeldung.domain;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class VertragHausrat {

	private Integer quadratMeter;

	private Boolean glasVersichert;
}
