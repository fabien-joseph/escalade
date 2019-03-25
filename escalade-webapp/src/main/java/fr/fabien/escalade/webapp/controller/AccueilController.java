package fr.fabien.escalade.webapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.LinkedHashMap;
import java.util.Map;

import static fr.fabien.escalade.business.Departements.departements;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class AccueilController {
    @GetMapping
    public String listDepartements(Model model) {
        model.addAttribute("departements", departements);
        return "accueil";
    }
}
