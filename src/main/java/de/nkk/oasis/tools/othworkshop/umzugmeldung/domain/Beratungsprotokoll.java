package de.nkk.oasis.tools.othworkshop.umzugmeldung.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Beratungsprotokoll {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long vertragId;

    private String vsnr;

    private String sparte;

}
