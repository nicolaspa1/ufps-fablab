package com.ufps.fablab;

import com.ufps.fablab.domain.service.RolService;
import com.ufps.fablab.domain.service.UsuarioService;
import com.ufps.fablab.persistence.entity.Rol;
import com.ufps.fablab.persistence.entity.Usuario;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UsuarioTest {

    private static final int MAX = 100000;
    @Autowired
    private UsuarioService servicio;
    @Autowired
    private RolService rolService;

    @Test
    void crud() {
        Usuario expected, actual;
        ArrayList<Usuario> listaExpected = (ArrayList<Usuario>) servicio.findAll();
        for (int i = 0; i < 10; i++) {
            expected = new Usuario();
            expected.setNombre("nombre: " + i);
            expected.setApellido("apellido: " + i);
            expected.setAlias("alias: " + (MAX + i));
            expected.setContrasena("contrasena: " + i);
            // test: save
            servicio.save(expected);
            actual = servicio.findById(expected.getId());
            assertEquals(expected, actual);
            expected.setNombre("nombre: " + (i + 1));
            expected.setApellido("apellido: " + (i + 1));
            expected.setAlias("alias: " + (2 * MAX + i));
            expected.setContrasena("contrasena: " + (i + 1));
            // test: save
            servicio.save(expected);
            actual = servicio.findById(expected.getId());
            assertEquals(expected, actual);
            // test: deleteById
            servicio.deleteById(expected.getId());
            actual = servicio.findById(expected.getId());
            assertNull(actual);
        }
        ArrayList<Usuario> listaActual = (ArrayList<Usuario>) servicio.findAll();
        assertEquals(listaExpected, listaActual);
    }

    @Test
    void remitenteReceptor() {
        Usuario remitente, receptor;
        ArrayList<Usuario> listaUsuarioExpected = (ArrayList<Usuario>) servicio.findAll();
        for (int i = 0; i < 10; i++) {
            remitente = new Usuario();
            remitente.setNombre("remitente nombre: " + (i + 1));
            remitente.setApellido("remitente apellido: " + (i + 1));
            remitente.setAlias("remitente alias: " + (3 * MAX + i + 1));
            remitente.setContrasena("remitente contrasena: " + (i + 1));
            receptor = new Usuario();
            receptor.setNombre("receptor nombre: " + (i + 1));
            receptor.setApellido("receptor apellido: " + (i + 1));
            receptor.setAlias("receptor alias: " + (4 * MAX + i));
            receptor.setContrasena("receptor contrasena: " + (i + 1));
            servicio.save(remitente);
            servicio.save(receptor);
            servicio.deleteById(remitente.getId());
            servicio.deleteById(receptor.getId());
        }
        ArrayList<Usuario> listaUsuarioActual = (ArrayList<Usuario>) servicio.findAll();
        assertEquals(listaUsuarioExpected, listaUsuarioActual);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.MINUTES)
    void stress() {
        Usuario expected;
        ArrayList<Usuario> listaExpected = (ArrayList<Usuario>) servicio.findAll();
        for (int i = 0; i < 1000; i++) {
            expected = new Usuario();
            expected.setNombre("nombre: " + i);
            expected.setApellido("apellido: " + i);
            expected.setAlias("alias: " + (5 * MAX + i));
            expected.setContrasena("contrasena: " + i);
            servicio.save(expected);
            servicio.deleteById(expected.getId());
        }
        ArrayList<Usuario> listaActual = (ArrayList<Usuario>) servicio.findAll();
        assertEquals(listaExpected, listaActual);
    }

    @Test
    void agregarRoles() {
        Usuario expected, actual;
        Rol rol;
        ArrayList<Usuario> listaExpected = (ArrayList<Usuario>) servicio.findAll();
        for (int i = 0; i < 10; i++) {
            expected = new Usuario();
            expected.setNombre("nombre: " + i);
            expected.setApellido("apellido: " + i);
            expected.setAlias("alias: " + (6 * MAX + i));
            expected.setContrasena("contrasena: " + i);
            rol = rolService.findById((long) (i % 3 + 1));
            assertNotNull(rol);
            servicio.save(expected);
            servicio.agregarRol(expected.getId(), rol.getId());
            actual = servicio.findById(expected.getId());
            assertEquals(expected, actual);
            servicio.deleteById(expected.getId());
        }
        ArrayList<Usuario> listaActual = (ArrayList<Usuario>) servicio.findAll();
        assertEquals(listaExpected, listaActual);
    }
}
