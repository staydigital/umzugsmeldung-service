package de.nkk.oasis.tools.othworkshop.umzugmeldung.controller;


import de.nkk.oasis.tools.othworkshop.umzugmeldung.domain.Vertrag;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class VertragTest {

    @Autowired
    private TestEntityManager em;

    @Test
    public void shouldPersistAndFind() {
        Vertrag vertrag = StaticTesterMethods.createVertrag("XYZ", StaticTesterMethods.createAdresse("Musterweg 25", "12345", "Musterstadt", "Deutschland"));
        em.persistFlushFind(vertrag.getAdresse());
        Vertrag dbo = em.persistFlushFind(vertrag);
        StaticTesterMethods.assertVertrag(vertrag, dbo);
    }

    @Test
    public void shouldPersistLVAndFind() {
        Vertrag vertrag = StaticTesterMethods.createLVVertrag("XYZ", StaticTesterMethods.createAdresse("Musterweg 25", "12345", "Musterstadt", "Deutschland"), "LVPROP1");
        em.persistFlushFind(vertrag.getAdresse());
        Vertrag dbo = em.persistFlushFind(vertrag);
        StaticTesterMethods.assertLVVertrag(vertrag, dbo);
    }

    @Test
    public void shouldPersistPKWAndFind() {
        Vertrag vertrag = StaticTesterMethods.createPKWVertrag("XYZ", StaticTesterMethods.createAdresse("Musterweg 25", "12345", "Musterstadt", "Deutschland"), "R-A 923");
        em.persistFlushFind(vertrag.getAdresse());
        Vertrag dbo = em.persistFlushFind(vertrag);
        StaticTesterMethods.assertPkwVertrag(vertrag, dbo);
    }
}
