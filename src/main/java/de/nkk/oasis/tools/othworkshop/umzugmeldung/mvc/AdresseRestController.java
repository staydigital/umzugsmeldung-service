package de.nkk.oasis.tools.othworkshop.umzugmeldung.mvc;

import de.nkk.oasis.tools.othworkshop.umzugmeldung.domain.Adresse;
import de.nkk.oasis.tools.othworkshop.umzugmeldung.domain.AdresseRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AdresseRestController {

    private AdresseRepository adresseRepository;

    public AdresseRestController(AdresseRepository adresseRepository) {
        this.adresseRepository = adresseRepository;
    }

    @GetMapping("/adressen")
    public List<Adresse> getAdressen() {
        return adresseRepository.findAll();
    }

    @GetMapping("/adresse/{id}")
    public Adresse getAdresse(@PathVariable Long id) {
        return adresseRepository.getOne(id);
    }
}
