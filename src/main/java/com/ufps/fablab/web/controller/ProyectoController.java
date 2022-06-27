package com.ufps.fablab.web.controller;


import com.ufps.fablab.domain.service.ProyectoService;
import com.ufps.fablab.persistence.entity.Proyecto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/proyecto")
@CrossOrigin
public class ProyectoController {

    @Autowired
    private ProyectoService proyectoService;

    @GetMapping("/{id}")
    public Proyecto buscar(@PathVariable Long id) {
        return this.proyectoService.findById(id);
    }

    @GetMapping
    public List<Proyecto> listar() {
        return this.proyectoService.findAll();
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Proyecto proyecto) {
        Map<String, Object> response = new HashMap<>();
        Proyecto u = this.proyectoService.findById(proyecto.getId());
        if (u == null) {
            this.proyectoService.save(proyecto);
            response.put("mensaje", "usuario creado correctamente");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
        } else {
            response.put("mensaje", "error al crear el curso");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void actualizar(@PathVariable Long id, @RequestBody Proyecto proyecto) {
        proyecto.setId(id);
        this.proyectoService.save(proyecto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void eliminar(@PathVariable Long id) {
        this.proyectoService.deleteById(id);
    }
}
