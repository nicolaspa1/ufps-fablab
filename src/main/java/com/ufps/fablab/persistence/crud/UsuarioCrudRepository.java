package com.ufps.fablab.persistence.crud;

import com.ufps.fablab.persistence.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioCrudRepository extends CrudRepository<Usuario, Long> {
    Usuario findByAlias(String alias);
}
