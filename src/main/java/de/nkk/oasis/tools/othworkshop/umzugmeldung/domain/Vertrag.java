package de.nkk.oasis.tools.othworkshop.umzugmeldung.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Vertrag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String vsnr;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adresse_id")
    private Adresse adresse;

    @Embedded
    private VertragLV lV;

    @Embedded
    private VertragKfz kfz;

    @Embedded
    private VertragHausrat hausrat;

    @Embedded
    private VertragUnfall unfall;

    @Embedded
    private VertragHaftpflicht haftpflicht;

    private String activityId;
}
