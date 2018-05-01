package de.nkk.oasis.tools.othworkshop.umzugmeldung.controller;


import de.nkk.oasis.tools.othworkshop.umzugmeldung.domain.Adresse;
import de.nkk.oasis.tools.othworkshop.umzugmeldung.domain.AdresseRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.mockito.Matchers.any;

@RunWith(SpringRunner.class)
@WebMvcTest
public class AdresseRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AdresseRepository adresseRepository;

    @Before
    public void bootstrap() {
        Adresse adresse1 = StaticTesterMethods.createAdresse(
                (Long) 1L, "Musterstraße 12", "12345", "Musterstadt", "Deutschland");
        Adresse adresse2 = StaticTesterMethods.createAdresse(
                (Long) 2L, "Roter Weg 25", "93057", "Regensburg", "Deutschland");
        Mockito.when(adresseRepository.findAll()).thenReturn(Arrays.asList(adresse1, adresse2));
        Mockito.when(adresseRepository.getOne(any(Long.class))).thenReturn(adresse1);
    }
    @Test
    public void shouldReturnResponseForAdressen() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/adressen"))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("@.[0].id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("@.[0].strasse").value("Musterstraße 12"))
                .andExpect(MockMvcResultMatchers.jsonPath("@.[0].plz").value("12345"))
                .andExpect(MockMvcResultMatchers.jsonPath("@.[0].wohnort").value("Musterstadt"))
                .andExpect(MockMvcResultMatchers.jsonPath("@.[0].land").value("Deutschland"))
                .andExpect(MockMvcResultMatchers.jsonPath("@.[1].id").value("2"))
                .andExpect(MockMvcResultMatchers.jsonPath("@.[1].strasse").value("Roter Weg 25"))
                .andExpect(MockMvcResultMatchers.jsonPath("@.[1].plz").value("93057"))
                .andExpect(MockMvcResultMatchers.jsonPath("@.[1].wohnort").value("Regensburg"))
                .andExpect(MockMvcResultMatchers.jsonPath("@.[1].land").value("Deutschland"));

    }

    @Test
    public void shouldReturnResponseForAdresseWithId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/adresse/1"))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("@.id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("@.strasse").value("Musterstraße 12"))
                .andExpect(MockMvcResultMatchers.jsonPath("@.plz").value("12345"))
                .andExpect(MockMvcResultMatchers.jsonPath("@.wohnort").value("Musterstadt"))
                .andExpect(MockMvcResultMatchers.jsonPath("@.land").value("Deutschland"));
    }
}
