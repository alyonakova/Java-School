package ru.t_systems.alyona.sbb.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.t_systems.alyona.sbb.service.SegmentService;

@Controller
@Data
@RequiredArgsConstructor
public class SegmentController {

    private final SegmentService segmentService;

    @GetMapping(value = "/timetable")
    public String timetable(Model model) {
        model.addAttribute("allSegments", segmentService.getAllSegments());
        return "timetable";
    }
}
