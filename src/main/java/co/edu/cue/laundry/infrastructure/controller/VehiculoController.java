package co.edu.cue.laundry.infrastructure.controller;

import co.edu.cue.laundry.infrastructure.utils.ResponseMessageUtil;
import co.edu.cue.laundry.mapping.dtos.*;
import co.edu.cue.laundry.services.ClienteService;
import co.edu.cue.laundry.services.EmpleadoService;
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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
@RequestMapping("vehiculos")
public class VehiculoController {
    private final VehiculoService service;
    private final ClienteService clienteService;

    @GetMapping
    public String listarVehiculos(Model model, HttpServletRequest request) {
        model.addAttribute("activeMenu", "vehiculos"); // Identificador del menú activo
        model.addAttribute("vehiculos", service.getAllElements());
        model.addAttribute("titulo", "Listado de Vehiculos");
        return "vehiculos/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String id = LocalDateTime.now().format(formatter);
        model.addAttribute("vehiculoForm", new VehiculoRequestDTO(id,"","","activo","",""));
        model.addAttribute("clientes", clienteService.getAllElements());
        model.addAttribute("titulo", "Nuevo Vehiculo");
        return "vehiculos/formulario";
    }

    @PostMapping("/guardar")
    public String guardarVehiculo(@Valid @ModelAttribute("vehiculoForm") VehiculoRequestDTO vehiculoRequestDTO,
                                  BindingResult result,
                                  Model model,
                                  RedirectAttributes redirectAttributes) {

        if(result.hasErrors()) {
            model.addAttribute("titulo", "Nuevo Vehiculo");
            return "vehiculos/formulario";
        }

        service.createElement(vehiculoRequestDTO);
        redirectAttributes.addFlashAttribute("success", "Vehiculo creado exitosamente");
        return "redirect:/vehiculos";
    }

    @GetMapping("/ver/{id}")
    public String verVehiculo(@PathVariable String id, Model model) {
        VehiculoDTO vehiculoDTO = service.getOneElement(id);
        model.addAttribute("vehiculo", vehiculoDTO);
        model.addAttribute("titulo", "Detalles del Vehiculo");
        return "vehiculos/detalle";
    }

    @PostMapping("/delete/{vehiculo}")
    public String eliminarVehiculo(@PathVariable String vehiculo, RedirectAttributes redirectAttributes) {
        service.deleteElement(vehiculo);
        redirectAttributes.addFlashAttribute("success", "Vehiculo eliminado exitosamente");
        return "redirect:/vehiculos";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable String id, Model model) {
        VehiculoDTO vehiculo = service.getOneElement(id);
        VehiculoRequestDTO form = new VehiculoRequestDTO(
                vehiculo.id(),
                vehiculo.tipo(),
                vehiculo.descripcion(),
                vehiculo.estado(),
                vehiculo.cliente().getId(),
                vehiculo.placa()
        );
        model.addAttribute("vehiculoForm", form);
        model.addAttribute("clientes", clienteService.getAllElements());
        model.addAttribute("titulo", "Editar Vehículo");
        return "vehiculos/formulario_editar";
    }

    @PostMapping("/editar")
    public String editarVehiculo(@Valid @ModelAttribute("vehiculoForm") VehiculoRequestDTO vehiculoRequestDTO,
                                 BindingResult result,
                                 Model model,
                                 RedirectAttributes redirectAttributes) {

        if(result.hasErrors()) {
            model.addAttribute("titulo", "Editar Vehículo");
            model.addAttribute("clientes", clienteService.getAllElements());
            return "vehiculos/formulario_editar";
        }

        service.updateElement(vehiculoRequestDTO);
        redirectAttributes.addFlashAttribute("success", "Vehículo actualizado exitosamente");
        return "redirect:/vehiculos";
    }


}
