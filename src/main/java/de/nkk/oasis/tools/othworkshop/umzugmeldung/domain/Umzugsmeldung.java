package de.nkk.oasis.tools.othworkshop.umzugmeldung.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Umzugsmeldung {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long vnId;

    private Long adresseId;

}
