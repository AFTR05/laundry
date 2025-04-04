package co.edu.cue.laundry.infrastructure.controller;

import co.edu.cue.laundry.mapping.dtos.ClienteDTO;
import co.edu.cue.laundry.mapping.dtos.ClienteRequestDTO;
import co.edu.cue.laundry.mapping.dtos.EmpleadoRequestDTO;
import co.edu.cue.laundry.services.ClienteService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@AllArgsConstructor
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService service;

    @GetMapping
    public String listarClientes(Model model, HttpServletRequest request) {
        model.addAttribute("activeMenu", "clientes"); // Identificador del menú activo
        model.addAttribute("clientes", service.getAllElements());
        model.addAttribute("titulo", "Listado de Clientes");
        return "clientes/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        // Crear un nuevo objeto para el formulario
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String id = LocalDateTime.now().format(formatter);
        model.addAttribute("clienteForm", new ClienteRequestDTO(id,"", "", "", "activo"));
        model.addAttribute("titulo", "Nuevo Cliente");
        return "clientes/formulario";
    }

    @PostMapping("/guardar")
    public String guardarCliente(@Valid @ModelAttribute("clienteForm") ClienteRequestDTO clienteRequest,
                                 BindingResult result,
                                 Model model,
                                 RedirectAttributes redirectAttributes) {

        if(result.hasErrors()) {
            model.addAttribute("titulo", "Nuevo Cliente");
            return "clientes/formulario";
        }

        service.createElement(clienteRequest);
        redirectAttributes.addFlashAttribute("success", "Cliente creado exitosamente");
        return "redirect:/clientes";
    }

    @GetMapping("/ver/{id}")
    public String verCliente(@PathVariable String id, Model model) {
        ClienteDTO cliente = service.getOneElement(id);
        model.addAttribute("cliente", cliente);
        model.addAttribute("titulo", "Detalles del Cliente");
        return "clientes/detalle";
    }

    @PostMapping("/delete/{id}")
    public String eliminarCliente(@PathVariable String id, RedirectAttributes redirectAttributes) {
        service.deleteElement(id);
        redirectAttributes.addFlashAttribute("success", "Cliente eliminado exitosamente");
        return "redirect:/clientes";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable String id, Model model) {
        ClienteDTO cliente = service.getOneElement(id);
        ClienteRequestDTO clienteForm = new ClienteRequestDTO(
                cliente.id(),
                cliente.nombre(),
                cliente.distrito(),
                cliente.direccion(),
                cliente.estado()
        );
        model.addAttribute("clienteForm", clienteForm);
        model.addAttribute("titulo", "Editar Cliente");
        return "clientes/formulario_editar";
    }

    @PostMapping("/editar")
    public String editarCliente(@Valid @ModelAttribute("clienteForm") ClienteRequestDTO clienteRequest,
                                BindingResult result,
                                Model model,
                                RedirectAttributes redirectAttributes) {

        if(result.hasErrors()) {
            model.addAttribute("titulo", "Editar Cliente");
            return "clientes/formulario_editar";
        }

        service.updateElement(clienteRequest);
        redirectAttributes.addFlashAttribute("success", "Cliente actualizado exitosamente");
        return "redirect:/clientes";
    }



}