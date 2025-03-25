package co.edu.cue.laundry.infrastructure.controller;

import co.edu.cue.laundry.infrastructure.utils.ResponseMessageUtil;
import co.edu.cue.laundry.mapping.dtos.*;
import co.edu.cue.laundry.services.EmpleadoService;
import co.edu.cue.laundry.services.InsumoService;
import co.edu.cue.laundry.services.InventarioService;
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
@RequestMapping("inventarios")
public class InventarioController {
    private final InventarioService service;
    private final InsumoService insumoService;
    @GetMapping
    public String listarInventarios(Model model, HttpServletRequest request) {
        model.addAttribute("activeMenu", "inventarios"); // Identificador del menú activo
        model.addAttribute("inventarios", service.getAllElements());
        model.addAttribute("titulo", "Listado de Inventarios");
        return "inventarios/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String id = LocalDateTime.now().format(formatter);
        model.addAttribute("inventarioForm", new InventarioRequestDTO("",0,"activo"));
        model.addAttribute("insumos", insumoService.getAllElements());
        model.addAttribute("titulo", "Nuevo Inventario");
        return "inventarios/formulario";
    }

    @PostMapping("/guardar")
    public String guardarInventario(@Valid @ModelAttribute("inventarioForm") InventarioRequestDTO inventarioRequestDTO,
                                  BindingResult result,
                                  Model model,
                                  RedirectAttributes redirectAttributes) {

        if(result.hasErrors()) {
            model.addAttribute("titulo", "Nuevo Inventario");
            return "inventarios/formulario";
        }

        service.createElement(inventarioRequestDTO);
        redirectAttributes.addFlashAttribute("success", "Inventario creado exitosamente");
        return "redirect:/inventarios";
    }

    @GetMapping("/ver/{id}")
    public String verInventario(@PathVariable Integer id, Model model) {
        InventarioDTO inventarioDTO = service.getOneElement(id);
        model.addAttribute("inventario", inventarioDTO);
        model.addAttribute("titulo", "Detalles del Inventario");
        return "inventarios/detalle";
    }
}
