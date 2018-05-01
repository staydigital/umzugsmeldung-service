package de.nkk.oasis.tools.othworkshop.umzugmeldung.controller;

import de.nkk.oasis.tools.othworkshop.umzugmeldung.domain.Adresse;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AdresseTest {

    @Autowired
    private TestEntityManager em;

    @Test
    public void shouldPersistAndFind() {
        Adresse addr = StaticTesterMethods.createAdresse("Musterweg 25", "12345", "Musterstadt", "Deutschland");

        Adresse rraddr = em.persistFlushFind(addr);
        Assertions.assertThat(rraddr.getId()).isNotNull();

        StaticTesterMethods.assertAdresse(rraddr, addr);
    }


}
