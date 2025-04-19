package co.edu.cue.laundry.infrastructure.controller;

import co.edu.cue.laundry.domain.entities.Proveedor;
import co.edu.cue.laundry.mapping.dtos.*;
import co.edu.cue.laundry.mapping.mappers.InsumoMapper;
import co.edu.cue.laundry.services.InsumoService;
import co.edu.cue.laundry.services.OfertaInsumoService;
import co.edu.cue.laundry.services.ServicioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping("ofertaInsumo")
public class OfertaInsumoController {

    private final OfertaInsumoService service;
    private final InsumoService insumoService;
    private final InsumoMapper insumoMapper;

    @GetMapping
    public String listarOfertasInsumos(Model model, HttpServletRequest request, HttpSession session) {
        ProveedorDTO proveedor = (ProveedorDTO) session.getAttribute("proveedor");
        List<OfertaInsumoDTO> ofertasProveedor = service.getAllElements().stream()
                .filter(oferta -> oferta.proveedor().getId().equals(proveedor.id()))
                .toList();
        model.addAttribute("ofertasInsumos", ofertasProveedor);
        model.addAttribute("titulo", "Listado de Servicios");
        return "ofertaInsumo/lista";
    }


    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model, HttpSession session) {
        ProveedorDTO proveedor = (ProveedorDTO) session.getAttribute("proveedor");
        if (proveedor == null) {
            return "redirect:/login";
        }
        List<InsumoDTO> todosInsumos = insumoService.getAllElements();
        Set<String> insumosYaOfertados = service.getAllElements().stream()
                .filter(oferta -> oferta.proveedor().getId().equals(proveedor.id()))
                .map(oferta -> oferta.insumo().getId())
                .collect(Collectors.toSet());
        List<InsumoDTO> insumosDisponibles = todosInsumos.stream()
                .filter(insumo -> !insumosYaOfertados.contains(insumo.id()))
                .toList();
        model.addAttribute("ofertaInsumoForm", new OfertaInsumoRequestDTO(
                0.0,
                "",
                proveedor.id()
        ));
        model.addAttribute("insumos", insumosDisponibles);
        model.addAttribute("titulo", "Nueva oferta de insumo");

        return "ofertaInsumo/formulario";
    }



    @PostMapping("/guardar")
    public String guardarOfertaInsumo(@Valid @ModelAttribute("ofertaInsumoForm") OfertaInsumoRequestDTO ofertaInsumoRequestDTO,
                                  BindingResult result,
                                  Model model,
                                  RedirectAttributes redirectAttributes) {

        if(result.hasErrors()) {
            model.addAttribute("titulo", "Nuevo oferta de insumo");
            return "ofertaInsumo/formulario";
        }

        service.createElement(ofertaInsumoRequestDTO);
        redirectAttributes.addFlashAttribute("success", "Oferta de insumo creado exitosamente");
        return "redirect:/ofertaInsumo";
    }

    @GetMapping("/ver/{id}")
    public String verOfertaInsumo(@PathVariable Integer id, Model model) {
        OfertaInsumoDTO ofertaInsumoDTO = service.getOneElement(id);
        model.addAttribute("ofertaInsumo", ofertaInsumoDTO);
        model.addAttribute("titulo", "Detalles de la oferta de servicio");
        return "ofertaInsumo/detalle";
    }
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Integer id, Model model, HttpSession session) {
        ProveedorDTO proveedor = (ProveedorDTO) session.getAttribute("proveedor");
        if (proveedor == null) {
            return "redirect:/login";
        }
        OfertaInsumoDTO ofertaInsumoDTO = service.getOneElement(id);
        List<InsumoDTO> insumosDisponibles = new ArrayList<>(insumoService.getAllElements().stream()
                .filter(insumo ->
                        service.getAllElements().stream()
                                .noneMatch(oferta ->
                                        oferta.proveedor().getId().equals(proveedor.id()) &&
                                                !oferta.id().equals(ofertaInsumoDTO.id()) &&
                                                oferta.insumo().getId().equals(insumo.id())
                                )
                )
                .toList());
        if (insumosDisponibles.stream().noneMatch(i -> i.id().equals(ofertaInsumoDTO.insumo().getId()))) {
            insumosDisponibles.add(insumoService.getOneElement(ofertaInsumoDTO.insumo().getId()));
        }
        OfertaInsumoUpdateDTO form = new OfertaInsumoUpdateDTO(
                ofertaInsumoDTO.id(),
                ofertaInsumoDTO.precio(),
                ofertaInsumoDTO.insumo().getId(),
                ofertaInsumoDTO.proveedor().getId()
        );
        model.addAttribute("ofertaInsumoForm", form);
        model.addAttribute("insumos", insumosDisponibles);
        model.addAttribute("titulo", "Editar oferta de servicio");
        return "ofertaInsumo/formulario_editar";
    }
    @PostMapping("/editar")
    public String editar(@Valid @ModelAttribute("ofertaInsumoForm") OfertaInsumoUpdateDTO updateDTO,
                                   BindingResult result,
                                   Model model,
                                   RedirectAttributes redirectAttributes, HttpSession session
                         ) {
        ProveedorDTO proveedor = (ProveedorDTO) session.getAttribute("proveedor");
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Editar Inventario");
            List<InsumoDTO> insumosDisponibles = new ArrayList<>(insumoService.getAllElements().stream()
                    .filter(insumo ->
                            service.getAllElements().stream()
                                    .noneMatch(oferta ->
                                            oferta.proveedor().getId().equals(proveedor.id()) &&
                                                    !oferta.id().equals(updateDTO.id()) &&
                                                    oferta.insumo().getId().equals(insumo.id())
                                    )
                    )
                    .toList());
            if (insumosDisponibles.stream().noneMatch(i -> i.id().equals(updateDTO.insumo_id()))) {
                insumosDisponibles.add(insumoService.getOneElement(updateDTO.insumo_id()));
            }
            model.addAttribute("insumos", insumoService.getAllElements());
            return "ofertaInsumo/formulario_editar";
        }
        service.updateElement(updateDTO);
        redirectAttributes.addFlashAttribute("success", "Servicio actualizado exitosamente");
        return "redirect:/ofertaInsumo";
    }
}
