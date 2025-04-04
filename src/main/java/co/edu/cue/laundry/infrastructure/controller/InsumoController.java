package co.edu.cue.laundry.infrastructure.controller;

import co.edu.cue.laundry.infrastructure.utils.ResponseMessageUtil;
import co.edu.cue.laundry.mapping.dtos.EmpleadoDTO;
import co.edu.cue.laundry.mapping.dtos.EmpleadoRequestDTO;
import co.edu.cue.laundry.mapping.dtos.InsumoDTO;
import co.edu.cue.laundry.mapping.dtos.InsumoRequestDTO;
import co.edu.cue.laundry.services.EmpleadoService;
import co.edu.cue.laundry.services.InsumoService;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
@RequestMapping("insumos")
public class InsumoController {
    private final InsumoService service;
    private final TipoInsumoService tipoInsumoService;

    @GetMapping
    public String listarInsumos(Model model, HttpServletRequest request) {
        model.addAttribute("activeMenu", "insumos"); // Identificador del menú activo
        model.addAttribute("insumos", service.getAllElements());
        model.addAttribute("titulo", "Listado de Insumos");
        return "insumos/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String id = LocalDateTime.now().format(formatter);
        model.addAttribute("insumoForm", new InsumoRequestDTO(id,"",0.0,"activo",""));
        model.addAttribute("tipoInsumos", tipoInsumoService.getAllElements());
        model.addAttribute("titulo", "Nuevo Insumo");
        return "insumos/formulario";
    }

    @PostMapping("/guardar")
    public String guardarEmpleado(@Valid @ModelAttribute("insumoForm") InsumoRequestDTO insumoRequestDTO,
                                  BindingResult result,
                                  Model model,
                                  RedirectAttributes redirectAttributes) {

        if(result.hasErrors()) {
            model.addAttribute("titulo", "Nuevo Insumo");
            return "insumos/formulario";
        }

        service.createElement(insumoRequestDTO);
        redirectAttributes.addFlashAttribute("success", "Insumo creado exitosamente");
        return "redirect:/insumos";
    }

    @GetMapping("/ver/{id}")
    public String verInsumo(@PathVariable String id, Model model) {
        InsumoDTO insumo = service.getOneElement(id);
        model.addAttribute("insumo", insumo);
        model.addAttribute("titulo", "Detalles del Insumo");
        return "insumos/detalle";
    }

    @PostMapping("/delete/{insumo}")
    public String eliminarInsumo(@PathVariable String insumo,RedirectAttributes redirectAttributes) {
        service.deleteElement(insumo);
        redirectAttributes.addFlashAttribute("success", "Insumo eliminado exitosamente");
        return "redirect:/insumos";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable String id, Model model) {
        InsumoDTO insumo = service.getOneElement(id);
        InsumoRequestDTO form = new InsumoRequestDTO(
                insumo.id(),
                insumo.nombre(),
                insumo.precio(),
                insumo.estado(),
                insumo.tipoInsumo().getId()
        );
        model.addAttribute("insumoForm", form);
        model.addAttribute("tipoInsumos", tipoInsumoService.getAllElements());
        model.addAttribute("titulo", "Editar Insumo");
        return "insumos/formulario_editar";
    }

    @PostMapping("/editar")
    public String editarInsumo(@Valid @ModelAttribute("insumoForm") InsumoRequestDTO insumoRequestDTO,
                               BindingResult result,
                               Model model,
                               RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            model.addAttribute("titulo", "Editar Insumo");
            model.addAttribute("tipoInsumos", tipoInsumoService.getAllElements());
            return "insumos/formulario_editar";
        }

        service.updateElement(insumoRequestDTO);
        redirectAttributes.addFlashAttribute("success", "Insumo actualizado exitosamente");
        return "redirect:/insumos";
    }


}
