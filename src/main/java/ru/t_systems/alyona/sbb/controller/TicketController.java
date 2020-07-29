package ru.t_systems.alyona.sbb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.t_systems.alyona.sbb.dto.SegmentsGroupDTO;

@Controller
@RequiredArgsConstructor
public class TicketController {


    @PostMapping(value = "/tickets")
    public String showTicketPurchasePage(@ModelAttribute SegmentsGroupDTO segmentsGroupDTO, Model model) {
        model.addAttribute("segments", segmentsGroupDTO.getSegments());
        return "buyTickets";
    }
}
