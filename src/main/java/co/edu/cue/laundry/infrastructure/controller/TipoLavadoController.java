package co.edu.cue.laundry.infrastructure.controller;

import co.edu.cue.laundry.infrastructure.repository.InsumoRepository;
import co.edu.cue.laundry.infrastructure.utils.ResponseMessageUtil;
import co.edu.cue.laundry.mapping.dtos.*;
import co.edu.cue.laundry.services.EmpleadoService;
import co.edu.cue.laundry.services.InsumoService;
import co.edu.cue.laundry.services.TipoLavadoService;
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
@RequestMapping("tiposLavado")
public class TipoLavadoController {
    private final TipoLavadoService service;
    private final InsumoService insumoService;

    @GetMapping
    public String listarTiposLavado(Model model, HttpServletRequest request) {
        model.addAttribute("activeMenu", "tiposLavado"); // Identificador del menú activo
        model.addAttribute("tiposLavado", service.getAllElements());
        model.addAttribute("titulo", "Listado de Tipos de lavado");
        return "tiposLavado/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String id = LocalDateTime.now().format(formatter);
        model.addAttribute("tipoLavadoForm", new TipoLavadoRequestDTO(id,"activo","","",""));
        model.addAttribute("insumos", insumoService.getAllElements());
        model.addAttribute("titulo", "Nuevo Tipo de lavado");
        return "tiposLavado/formulario";
    }

    @PostMapping("/guardar")
    public String guardarTipoLavado(@Valid @ModelAttribute("tipoLavadoForm") TipoLavadoRequestDTO tipoLavadoRequestDTO,
                                  BindingResult result,
                                  Model model,
                                  RedirectAttributes redirectAttributes) {

        if(result.hasErrors()) {
            model.addAttribute("titulo", "Nuevo Tipo de lavado");
            return "tiposLavado/formulario";
        }

        service.createElement(tipoLavadoRequestDTO);
        redirectAttributes.addFlashAttribute("success", "Tipo de lavado creado exitosamente");
        return "redirect:/tiposLavado";
    }

    @GetMapping("/ver/{id}")
    public String verTipoLavado(@PathVariable String id, Model model) {
        TipoLavadoDTO tipoLavadoDTO = service.getOneElement(id);
        model.addAttribute("tipoLavado", tipoLavadoDTO);
        model.addAttribute("titulo", "Detalles del Tipo de lavado");
        return "tiposLavado/detalle";
    }
}
