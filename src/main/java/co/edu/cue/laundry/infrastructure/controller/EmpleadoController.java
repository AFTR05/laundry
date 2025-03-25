package co.edu.cue.laundry.infrastructure.controller;

import co.edu.cue.laundry.infrastructure.utils.ResponseMessageUtil;
import co.edu.cue.laundry.mapping.dtos.ClienteDTO;
import co.edu.cue.laundry.mapping.dtos.ClienteRequestDTO;
import co.edu.cue.laundry.mapping.dtos.EmpleadoDTO;
import co.edu.cue.laundry.mapping.dtos.EmpleadoRequestDTO;
import co.edu.cue.laundry.services.ClienteService;
import co.edu.cue.laundry.services.EmpleadoService;
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
@RequestMapping("/empleados")
public class EmpleadoController {

    private final EmpleadoService service;

    @GetMapping
    public String listarEmpleados(Model model, HttpServletRequest request) {
        model.addAttribute("activeMenu", "empleados"); // Identificador del menú activo
        model.addAttribute("empleados", service.getAllElements());
        model.addAttribute("titulo", "Listado de Empleados");
        return "empleados/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String id = LocalDateTime.now().format(formatter);
        model.addAttribute("empleadoForm", new EmpleadoRequestDTO(id,"","", LocalDate.now(), "activo"));
        model.addAttribute("titulo", "Nuevo Empleado");
        return "empleados/formulario";
    }

    @PostMapping("/guardar")
    public String guardarEmpleado(@Valid @ModelAttribute("empleadoForm") EmpleadoRequestDTO empleadoRequestDTO,
                                 BindingResult result,
                                 Model model,
                                 RedirectAttributes redirectAttributes) {

        if(result.hasErrors()) {
            model.addAttribute("titulo", "Nuevo Empleado");
            return "empleados/formulario";
        }

        service.createElement(empleadoRequestDTO);
        redirectAttributes.addFlashAttribute("success", "Empleado creado exitosamente");
        return "redirect:/empleados";
    }

    @GetMapping("/ver/{id}")
    public String verEmpleado(@PathVariable String id, Model model) {
        EmpleadoDTO empleado = service.getOneElement(id);
        model.addAttribute("empleado", empleado);
        model.addAttribute("titulo", "Detalles del Empleado");
        return "empleados/detalle";
    }
}
