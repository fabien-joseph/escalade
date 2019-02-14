package fr.fabien.escalade.webapp;

import fr.fabien.escalade.business.topo.TopoManagement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/test")
@RequiredArgsConstructor
public class AddController {
    private TopoManagement topoManagement;

    @RequestMapping(method = RequestMethod.GET)
    public String listUsers(Model model) {
        topoManagement.findAll();
        return "test";
    }
}
