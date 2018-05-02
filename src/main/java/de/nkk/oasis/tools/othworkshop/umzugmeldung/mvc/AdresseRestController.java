package de.nkk.oasis.tools.othworkshop.umzugmeldung.mvc;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import de.nkk.oasis.tools.othworkshop.umzugmeldung.domain.Adresse;
import de.nkk.oasis.tools.othworkshop.umzugmeldung.domain.AdresseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AdresseRestController {

    /*
    private AdresseRepository adresseRepository;

    public AdresseRestController(AdresseRepository adresseRepository) {
        this.adresseRepository = adresseRepository;
    }

    @GetMapping("/adressen")
    public List<Adresse> getAdressen() {
        return adresseRepository.findAll();
    }

    @GetMapping("/adresse/{id}")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public Adresse getAdresse(@PathVariable Long id) {
        Adresse adresse = adresseRepository.getOne(id);
        if (adresse.getId() != null) {
            return adresseRepository.getOne(id);
        }
        return null;
    }
    */
}
