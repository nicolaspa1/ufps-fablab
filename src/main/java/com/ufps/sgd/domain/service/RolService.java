package com.ufps.sgd.domain.service;

import com.ufps.sgd.persistence.crud.RolCrudRepository;
import com.ufps.sgd.persistence.entity.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RolService {

    @Autowired
    private RolCrudRepository rolCrudRepository;

    @Transactional(readOnly = true)
    public Rol findById(Long id) {
        return rolCrudRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Rol> findAll() {
        return (List<Rol>) rolCrudRepository.findAll();
    }

    @Transactional
    public void save(Rol t) {
        rolCrudRepository.save(t);
    }

    @Transactional
    public void deleteById(Long id) {
        rolCrudRepository.deleteById(id);
    }
}
