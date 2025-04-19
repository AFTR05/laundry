package co.edu.cue.laundry.infrastructure.controller;

import co.edu.cue.laundry.mapping.dtos.ClienteRequestDTO;
import co.edu.cue.laundry.mapping.dtos.ProveedorRequestDTO;
import co.edu.cue.laundry.services.ClienteService;
import co.edu.cue.laundry.services.ProveedorService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@AllArgsConstructor
@RequestMapping("/proveedor")
public class ProveedorController {

    private final ProveedorService service;

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("proveedorForm", new ProveedorRequestDTO("","", "", "", "",""));
        model.addAttribute("titulo", "Nuevo Proveedor");
        return "proveedor/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("proveedorForm") ProveedorRequestDTO proveedorRequestDTO,
                                 BindingResult result,
                                 Model model,
                                 RedirectAttributes redirectAttributes) {

        if(result.hasErrors()) {
            model.addAttribute("titulo", "Nuevo Proveedor");
            return "proveedor/formulario";
        }

        service.createElement(proveedorRequestDTO);
        redirectAttributes.addFlashAttribute("success", "Proveedor creado exitosamente");
        return "redirect:/login";
    }

}
