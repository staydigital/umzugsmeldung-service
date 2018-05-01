package de.nkk.oasis.tools.othworkshop.umzugmeldung.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class VN {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String vorname;
    private String nachname;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "adresse_id")
    private Adresse adresse;

    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Vertrag> vertraege;
}
