package com.ufps.fablab.persistence.crud;

import com.ufps.fablab.persistence.entity.Proyecto;
import org.springframework.data.repository.CrudRepository;

public interface ProyectoCrudRepository extends CrudRepository<Proyecto, Long> {
}
