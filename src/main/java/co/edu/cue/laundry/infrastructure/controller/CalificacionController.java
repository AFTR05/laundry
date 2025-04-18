package co.edu.cue.laundry.infrastructure.controller;

import co.edu.cue.laundry.mapping.dtos.*;
import co.edu.cue.laundry.services.CalificacionService;
import co.edu.cue.laundry.services.ClienteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/calificacion")
public class CalificacionController {

    private final CalificacionService service;

    @GetMapping("/ver/{id}")
    public String verInventario(@PathVariable Integer id, Model model) {
        CalificacionDTO calificacionDTO = service.getOneElement(id);
        model.addAttribute("calificacion", calificacionDTO);
        model.addAttribute("titulo", "Detalles de la calificación");
        return "inventarios/detalle";
    }

    @GetMapping("/calificar/{id}")
    public String calificarServicio(@PathVariable Integer id, Model model) {
        CalificacionRequestDTO calificacionDTO = new CalificacionRequestDTO(0.0, 0.0, 0.0, id);
        model.addAttribute("titulo", "Calificar Servicio");
        model.addAttribute("calificacion", calificacionDTO);
        return "servicios/formulario_calificacion";
    }

    @PostMapping("/guardar")
    public String guardarCalificacion(@ModelAttribute @Valid CalificacionRequestDTO calificacionDTO,
                                      BindingResult result,
                                      RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "servicios/formulario_calificacion";
        }
        service.createElement(calificacionDTO);
        redirectAttributes.addFlashAttribute("success", "Calificación registrada exitosamente.");
        return "redirect:/servicios";
    }

    @GetMapping("/estadisticas")
    public String verEstadisticas(Model model) {
        List<CalificacionDTO> calificaciones = service.getAllElements();

        double promedioEspera = calificaciones.stream().mapToDouble(CalificacionDTO::tiempoEspera).average().orElse(0.0);
        double promedioAmabilidad = calificaciones.stream().mapToDouble(CalificacionDTO::amabilidad).average().orElse(0.0);
        double promedioCalidad = calificaciones.stream().mapToDouble(CalificacionDTO::calidad).average().orElse(0.0);
        double promedioGeneral = calificaciones.stream()
                .mapToDouble(c -> c.tiempoEspera() + c.amabilidad() + c.calidad())
                .average().orElse(0.0) / 3.0;

        model.addAttribute("promedioEspera", promedioEspera);
        model.addAttribute("promedioAmabilidad", promedioAmabilidad);
        model.addAttribute("promedioCalidad", promedioCalidad);
        model.addAttribute("promedioGeneral", promedioGeneral);
        model.addAttribute("calificaciones", calificaciones);

        return "calificaciones/estadisticas";
    }





}
