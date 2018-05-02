package de.nkk.oasis.tools.othworkshop.umzugmeldung.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Umzugsmeldung {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long vnId;

    private Long adresseId;
}
