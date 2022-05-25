package com.ufps.sgd.persistence.crud;

import com.ufps.sgd.persistence.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioCrudRepository extends CrudRepository<Usuario, Long> {
    Usuario findByAlias(String alias);
}
