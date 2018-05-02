package de.nkk.oasis.tools.othworkshop.umzugmeldung.controller;

import de.nkk.oasis.tools.othworkshop.umzugmeldung.domain.*;
import org.assertj.core.api.Assertions;

import java.util.List;

class StaticTesterMethods {

    static Adresse createAdresse(Long id, String strasse, String plz, String wohnort, String land) {
        Adresse addr = createAdresse(strasse, plz, wohnort, land);
        addr.setId(id);
        return addr;
    }

    static Adresse createAdresse(String strasse, String plz, String wohnort, String land) {
        Adresse addr = new Adresse();
        addr.setStrasse(strasse);
        addr.setPlz(plz);
        addr.setOrt(wohnort);
        addr.setLand(land);
        return addr;
    }

    static VN createVN(String vorname, String nachname, Adresse adresse, List<Vertrag> vertraege) {
        VN vn = new VN();
        vn.setVorname(vorname);
        vn.setNachname(nachname);
        vn.setAdresse(adresse);
        vn.setVertraege(vertraege);
        return vn;
    }

    static Vertrag createVertrag(String vsnr, Adresse addr) {
        Vertrag vertrag = new Vertrag();
        vertrag.setVsnr(vsnr);
        vertrag.setAdresse(addr);
        return vertrag;
    }


    static Vertrag createLVVertrag(String vsnr, Adresse addr, String lVProp) {
        Vertrag vertrag = createVertrag(vsnr, addr);
        VertragLV vertragLV = new VertragLV();
        vertragLV.setLVProp(lVProp);
        vertrag.setLV(vertragLV);
        return vertrag;
    }

    static Vertrag createPKWVertrag(String vsnr, Adresse addr, String pkwProp) {
        Vertrag vertrag = createVertrag(vsnr, addr);
        VertragPkw vertragPkw = new VertragPkw();
        vertragPkw.setPkwProp(pkwProp);
        vertrag.setPkw(vertragPkw);
        return vertrag;
    }

    static void assertAdresse(final Adresse rraddr, final Adresse addr) {
        Assertions.assertThat(rraddr.getStrasse()).isEqualTo(addr.getStrasse());
        Assertions.assertThat(rraddr.getPlz()).isEqualTo(addr.getPlz());
        Assertions.assertThat(rraddr.getOrt()).isEqualTo(addr.getOrt());
        Assertions.assertThat(rraddr.getLand()).isEqualTo(addr.getLand());
    }

    static void assertVN(final VN rrvn, final VN vn) {
        assertAdresse(rrvn.getAdresse(), vn.getAdresse());
        for (int vertragIx = 0; vertragIx < rrvn.getVertraege().size(); vertragIx++) {
            assertVertrag(rrvn.getVertraege().get(vertragIx), vn.getVertraege().get(vertragIx));
        }
        Assertions.assertThat(rrvn.getVorname()).isEqualTo(vn.getVorname());
        Assertions.assertThat(rrvn.getNachname()).isEqualTo(vn.getNachname());
    }


    static void assertVertrag(Vertrag vertrag, Vertrag dbo) {
        assertAdresse(vertrag.getAdresse(), dbo.getAdresse());
        Assertions.assertThat(vertrag.getVsnr()).isEqualTo(dbo.getVsnr());
    }

    static void assertLVVertrag(Vertrag vertrag, Vertrag dbo) {
        assertVertrag(vertrag, dbo);
        Assertions.assertThat(vertrag.getLV().getLVProp()).isEqualTo(dbo.getLV().getLVProp());
    }

    static void assertPkwVertrag(Vertrag vertrag, Vertrag dbo) {
        assertVertrag(vertrag, dbo);
        Assertions.assertThat(vertrag.getPkw().getPkwProp()).isEqualTo(dbo.getPkw().getPkwProp());
    }
}
