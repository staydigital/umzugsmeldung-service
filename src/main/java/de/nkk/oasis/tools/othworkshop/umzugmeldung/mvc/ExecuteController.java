package de.nkk.oasis.tools.othworkshop.umzugmeldung.mvc;

import de.nkk.oasis.tools.othworkshop.umzugmeldung.domain.*;
import de.nkk.oasis.tools.othworkshop.umzugmeldung.process.UmzugMeldenOTHDefinitions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ExecuteController {

    @Autowired
    private UmzugsmeldungRepository umzugsmeldungRepository;

    @Autowired
    private UmzugMeldenOTHDefinitions umzugMeldenOTHDefinitions;

    @Autowired
    private VNRepository vnRepository;

    @GetMapping("/mockUmzug")
    public HttpStatus executeUmzugsmeldung() {

        umzugMeldenOTHDefinitions.mockUmzug();

        return HttpStatus.ACCEPTED;
    }

}
