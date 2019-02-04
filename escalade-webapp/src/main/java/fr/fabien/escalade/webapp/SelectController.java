package fr.fabien.escalade.webapp;

import fr.fabien.escalade.consumer.topo.TopoRepository;
import fr.fabien.escalade.model.topo.Topo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import java.util.List;

@Controller
public class SelectController {
    private TopoRepository topoRepository;

    @RequestMapping(name = "topo")
    public String topo() {
        ModelAndView mav = new ModelAndView();

        return "jsp/topo.jsp";
    }
}
