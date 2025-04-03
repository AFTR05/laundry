package co.edu.cue.laundry.infrastructure.controller;

import co.edu.cue.laundry.domain.entities.Servicio;
import co.edu.cue.laundry.infrastructure.utils.ResponseMessageUtil;
import co.edu.cue.laundry.mapping.dtos.*;
import co.edu.cue.laundry.services.EmpleadoService;
import co.edu.cue.laundry.services.ServicioService;
import co.edu.cue.laundry.services.TipoLavadoService;
import co.edu.cue.laundry.services.VehiculoService;
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
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
@RequestMapping("servicios")
public class ServicioController {
    private final ServicioService service;
    private final EmpleadoService empleadoService;
    private final VehiculoService vehiculoService;
    private final TipoLavadoService tipoLavadoService;

    @GetMapping
    public String listarServicios(Model model, HttpServletRequest request) {
        model.addAttribute("activeMenu", "servicios"); // Identificador del menú activo
        model.addAttribute("servicios", service.getAllElements());
        model.addAttribute("titulo", "Listado de Servicios");
        return "servicios/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("servicioForm",new ServicioRequestDTO(
                        Date.from(LocalDate.now().atStartOfDay(ZoneId.of("America/New_York")).toInstant()),
                        "","","","",
                        LocalTime.now(),LocalTime.now().plusHours(1),"",5.0,"activo","")
        );
        model.addAttribute("empleados", empleadoService.getAllElements());
        model.addAttribute("vehiculos",vehiculoService.getAllElements());
        model.addAttribute("tiposLavado", tipoLavadoService.getAllElements());
        model.addAttribute("titulo", "Nuevo Servicio");
        return "servicios/formulario";
    }

    @PostMapping("/guardar")
    public String guardarServicio(@Valid @ModelAttribute("servicioForm") ServicioRequestDTO servicioRequestDTO,
                                  BindingResult result,
                                  Model model,
                                  RedirectAttributes redirectAttributes) {

        if(result.hasErrors()) {
            model.addAttribute("titulo", "Nuevo Servicio");
            return "servicios/formulario";
        }

        service.createElement(servicioRequestDTO);
        redirectAttributes.addFlashAttribute("success", "Servicio creado exitosamente");
        return "redirect:/servicios";
    }

    @GetMapping("/ver/{id}")
    public String verServicio(@PathVariable Integer id, Model model) {
        ServicioDTO servicioDTO = service.getOneElement(id);
        model.addAttribute("servicio", servicioDTO);
        model.addAttribute("titulo", "Detalles del Servicio");
        return "servicios/detalle";
    }

    @PostMapping("/delete/{servicio}")
    public String eliminarServicio(@PathVariable Integer servicio,RedirectAttributes redirectAttributes) {
        service.deleteElement(servicio);
        redirectAttributes.addFlashAttribute("success", "Servicio eliminado exitosamente");
        return "redirect:/servicios";
    }
}
