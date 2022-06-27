package com.ufps.fablab.web.controller;


import com.ufps.fablab.domain.service.CursoService;
import com.ufps.fablab.persistence.entity.Curso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/curso")
@CrossOrigin
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping("/{id}")
    public Curso buscar(@PathVariable Long id) {
        return this.cursoService.findById(id);
    }

    @GetMapping
    public List<Curso> listar() {
        return this.cursoService.findAll();
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Curso curso) {
        Map<String, Object> response = new HashMap<>();
        Curso u = this.cursoService.findById(curso.getId());
        if (u == null) {
            this.cursoService.save(curso);
            response.put("mensaje", "usuario creado correctamente");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
        } else {
            response.put("mensaje", "error al crear el curso");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void actualizar(@PathVariable Long id, @RequestBody Curso curso) {
        curso.setId(id);
        this.cursoService.save(curso);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void eliminar(@PathVariable Long id) {
        this.cursoService.deleteById(id);
    }

}
