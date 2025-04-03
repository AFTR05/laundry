package co.edu.cue.laundry.infrastructure.controller;

import co.edu.cue.laundry.infrastructure.utils.ResponseMessageUtil;
import co.edu.cue.laundry.mapping.dtos.*;
import co.edu.cue.laundry.services.EmpleadoService;
import co.edu.cue.laundry.services.TipoInsumoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
@RequestMapping("/tiposInsumo")
public class TipoInsumoController {
    private final TipoInsumoService service;

    @GetMapping
    public String listarTipoInsumo(Model model, HttpServletRequest request) {
        model.addAttribute("activeMenu", "tiposInsumo");
        model.addAttribute("tiposInsumo", service.getAllElements());
        model.addAttribute("titulo", "Listado de Tipos de Insumo");
        return "tiposInsumo/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String id = LocalDateTime.now().format(formatter);
        model.addAttribute("tipoInsumoForm", new TipoInsumoRequestDTO(id,"","","activo"));
        model.addAttribute("titulo", "Nuevo Tipo de Insumo");
        return "tiposInsumo/formulario";
    }

    @PostMapping("/guardar")
    public String guardarTipoInsumo(@Valid @ModelAttribute("tipoInsumoForm") TipoInsumoRequestDTO tipoInsumoRequestDTO,
                                  BindingResult result,
                                  Model model,
                                  RedirectAttributes redirectAttributes) {

        if(result.hasErrors()) {
            model.addAttribute("titulo", "Nuevo Tipo de Insumo");
            return "tiposInsumo/formulario";
        }

        service.createElement(tipoInsumoRequestDTO);
        redirectAttributes.addFlashAttribute("success", "Tipo de Insumo creado exitosamente");
        return "redirect:/tiposInsumo";
    }

    @GetMapping("/ver/{id}")
    public String verTipoInsumo(@PathVariable String id, Model model) {
        TipoInsumoDTO tipoInsumoDTO = service.getOneElement(id);
        model.addAttribute("tiposInsumo", tipoInsumoDTO);
        model.addAttribute("titulo", "Detalles del Tipo de Insumo");
        return "tiposInsumo/detalle";
    }

    @PostMapping("/delete/{tipoInsumo}")
    public String eliminarTipoInsumo(@PathVariable String tipoInsumo, RedirectAttributes redirectAttributes) {
        service.deleteElement(tipoInsumo);
        redirectAttributes.addFlashAttribute("success", "Tipo de insumo eliminado exitosamente");
        return "redirect:/tiposInsumo";
    }
}
