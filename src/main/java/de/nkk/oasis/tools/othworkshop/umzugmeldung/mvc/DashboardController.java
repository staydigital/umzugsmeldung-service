package de.nkk.oasis.tools.othworkshop.umzugmeldung.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String server(Model model) {
        return "dashboard";
    }
}
