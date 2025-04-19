package co.edu.cue.laundry.infrastructure.controller;

import co.edu.cue.laundry.domain.entities.Proveedor;
import co.edu.cue.laundry.mapping.dtos.ProveedorDTO;
import co.edu.cue.laundry.mapping.dtos.ProveedorRequestDTO;
import co.edu.cue.laundry.services.ProveedorService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("login")
public class LoginController {

    private final ProveedorService service;

    @GetMapping
    public String mostrar(Model model) {
        model.addAttribute("proveedor", new ProveedorRequestDTO("", "", "", "", "", ""));
        return "login/login";
    }

    @PostMapping
    public String login(@ModelAttribute("proveedor") ProveedorRequestDTO proveedor, Model model, HttpSession session) {
        if (proveedor.usuario().equals("admin") && proveedor.contrasena().equals("@@@")) {
            return "redirect:/main";
        }

        ProveedorDTO encontrado = service.login(proveedor.usuario(), proveedor.contrasena());
        if (encontrado != null) {
            session.setAttribute("proveedor", encontrado);
            return "redirect:/ofertaInsumo";
        }

        model.addAttribute("proveedor", new ProveedorRequestDTO("", "", "", "", "", ""));
        model.addAttribute("error", "Usuario o contraseña incorrectos");
        return "login/login";
    }
}
