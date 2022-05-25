package com.ufps.fablab.domain.service;

import com.ufps.fablab.persistence.crud.UsuarioCrudRepository;
import com.ufps.fablab.persistence.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioCrudRepository usuarioCrudRepository;
    @Autowired
    private RolService rolService;

    @Transactional(readOnly = true)
    public Usuario findById(Long id) {
        return usuarioCrudRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Usuario> findAll() {
        return (List<Usuario>) usuarioCrudRepository.findAll();
    }

    @Transactional
    public void save(Usuario t) {
        t.setContrasena(DigestUtils.md5DigestAsHex(t.getContrasena().getBytes()));
        usuarioCrudRepository.save(t);
    }

    @Transactional
    public void deleteById(Long id) {
        usuarioCrudRepository.deleteById(id);
    }

    @Transactional
    public void agregarRol(Long idUsuario, Long idRol) {
        Usuario usuario = findById(idUsuario);
        usuario.agregarRol(rolService.findById(idRol));
    }

    @Transactional(readOnly = true)
    public Usuario findByAlias(String alias) {
        return this.usuarioCrudRepository.findByAlias(alias);
    }


    @Override
    public UserDetails loadUserByUsername(String alias) throws UsernameNotFoundException {
        Usuario usuario = usuarioCrudRepository.findByAlias(alias);
        return new User(usuario.getAlias(), "{noop}" + usuario.getContrasena(), new ArrayList<>());
    }
}
