package com.ufps.fablab.domain.service;

import com.ufps.fablab.persistence.crud.ProyectoCrudRepository;
import com.ufps.fablab.persistence.entity.Proyecto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProyectoService {

    @Autowired
    private ProyectoCrudRepository proyectoCrudRepository;

    @Transactional(readOnly = true)
    public Proyecto findById(Long id) {
        return proyectoCrudRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Proyecto> findAll() {
        return (List<Proyecto>) proyectoCrudRepository.findAll();
    }

    @Transactional
    public void save(Proyecto t) {
        proyectoCrudRepository.save(t);
    }

    @Transactional
    public void deleteById(Long id) {
        proyectoCrudRepository.deleteById(id);
    }
}
