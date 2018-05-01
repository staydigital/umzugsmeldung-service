package de.nkk.oasis.tools.othworkshop.umzugmeldung.controller;

import de.nkk.oasis.tools.othworkshop.umzugmeldung.domain.Adresse;
import de.nkk.oasis.tools.othworkshop.umzugmeldung.domain.VN;
import de.nkk.oasis.tools.othworkshop.umzugmeldung.domain.Vertrag;
import org.aspectj.lang.annotation.DeclareAnnotation;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class VNTest {

    @Autowired
    private TestEntityManager em;

    @Test
    public void shouldPersistAndFind() {
        List<Vertrag> vertraege = new ArrayList<>();
        Adresse addr = StaticTesterMethods.createAdresse("Musterstrasse 13", "12345", "Musterstadt", "Deutschland");
        vertraege.add(StaticTesterMethods.createLVVertrag("XYZ01", addr, "lvProp"));
        vertraege.add(StaticTesterMethods.createPKWVertrag("XYZ02", addr, "pkwProp"));

        VN vn = StaticTesterMethods.createVN("Max", "Mustermann", addr, vertraege);
        VN dbo = em.persistFlushFind(vn);
        Assertions.assertThat(dbo.getId()).isNotNull();
        Assertions.assertThat(dbo.getAdresse()).isNotNull();
        Assertions.assertThat(dbo.getVertraege()).isNotNull().isNotEmpty();
        StaticTesterMethods.assertVN(dbo, vn);
    }


}
