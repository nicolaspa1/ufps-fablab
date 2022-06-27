package com.ufps.fablab.domain.service;

import com.ufps.fablab.persistence.crud.CursoCrudRepository;
import com.ufps.fablab.persistence.entity.Curso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CursoService {

    @Autowired
    private CursoCrudRepository cursoCrudRepository;

    @Transactional(readOnly = true)
    public Curso findById(Long id) {
        return cursoCrudRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Curso> findAll() {
        return (List<Curso>) cursoCrudRepository.findAll();
    }

    @Transactional
    public void save(Curso t) {
        cursoCrudRepository.save(t);
    }

    @Transactional
    public void deleteById(Long id) {
        cursoCrudRepository.deleteById(id);
    }
}
