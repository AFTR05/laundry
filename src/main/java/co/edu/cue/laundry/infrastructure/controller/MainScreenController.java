package co.edu.cue.laundry.infrastructure.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/")
public class MainScreenController {

    @GetMapping
    public String mostrarPrincipal(Model model, HttpServletRequest request) {
        model.addAttribute("activeMenu", "main");
        model.addAttribute("titulo", "");
        return "main_screen/index";
    }
}
