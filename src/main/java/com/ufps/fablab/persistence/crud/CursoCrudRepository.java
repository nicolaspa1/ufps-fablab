package com.ufps.fablab.persistence.crud;

import com.ufps.fablab.persistence.entity.Curso;
import org.springframework.data.repository.CrudRepository;

public interface CursoCrudRepository extends CrudRepository<Curso, Long> {

}
