package co.edu.cue.laundry.infrastructure.controller;

import co.edu.cue.laundry.domain.entities.Servicio;
import co.edu.cue.laundry.infrastructure.utils.ResponseMessageUtil;
import co.edu.cue.laundry.mapping.dtos.*;
import co.edu.cue.laundry.services.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
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
    private final CalificacionService calificacionService;

    @GetMapping
    public String listarServicios(Model model, HttpServletRequest request) {
        model.addAttribute("activeMenu", "servicios");
        model.addAttribute("servicios", service.getAllElements());
        model.addAttribute("serviciosCalificados",
                calificacionService.getAllElements()
                        .stream()
                        .map(c -> c.servicio().getId().intValue())
                        .toList()
        );
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

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Integer id, Model model) {
        ServicioDTO servicioDTO = service.getOneElement(id);
        ServicioUpdateDTO form = new ServicioUpdateDTO(
                servicioDTO.id(),
                servicioDTO.fecha(),
                servicioDTO.empleadoRecibe().getId(),
                servicioDTO.empleadoLava().getId(),
                servicioDTO.tipoVehiculo().getId(),
                servicioDTO.tipoLavado().getId(),
                servicioDTO.horaRecibe(),
                servicioDTO.horaEntrega(),
                servicioDTO.tipoVehiculo().getPlaca(),
                servicioDTO.precio(),
                servicioDTO.estado(),
                servicioDTO.motivo()
        );
        model.addAttribute("servicioForm", form);
        model.addAttribute("empleados", empleadoService.getAllElements());
        model.addAttribute("vehiculos",vehiculoService.getAllElements());
        model.addAttribute("tiposLavado", tipoLavadoService.getAllElements());
        model.addAttribute("titulo", "Editar Servicio");
        return "servicios/formulario_editar";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }


    @PostMapping("/editar")
    public String editarInventario(@Valid @ModelAttribute("servicioForm") ServicioUpdateDTO updateDTO,
                                   BindingResult result,
                                   Model model,
                                   RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            model.addAttribute("titulo", "Editar Inventario");
            model.addAttribute("empleados", empleadoService.getAllElements());
            model.addAttribute("vehiculos",vehiculoService.getAllElements());
            model.addAttribute("tiposLavado", tipoLavadoService.getAllElements());
            return "servicios/formulario_editar";
        }

        service.updateElement(updateDTO);
        redirectAttributes.addFlashAttribute("success", "Servicio actualizado exitosamente");
        return "redirect:/servicios";
    }

}
