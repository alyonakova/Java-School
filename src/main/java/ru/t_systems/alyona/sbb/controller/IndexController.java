package ru.t_systems.alyona.sbb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.t_systems.alyona.sbb.dto.FindTrainFormDTO;

@Controller
public class IndexController {

    @GetMapping(value = "/")
    public String home(Model model) {
        model.addAttribute("findTrainFormDTO", new FindTrainFormDTO());
        return "index";
    }

}
