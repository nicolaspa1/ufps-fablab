package com.ufps.fablab.persistence.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String nombre;
    @NonNull
    private String apellido;
    @NonNull
    @Column(unique = true)
    private String alias;
    @NonNull
    private String contrasena;
    @NonNull
    private Boolean enabled;
    @NonNull
    private String genero;
    @NonNull
    private String email;
    @NonNull
    private String ciudad;
    @NonNull
    private String telefono;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"usuario_id", "rol_id"})})
    @EqualsAndHashCode.Exclude
    private Set<Rol> rol;
    public void agregarRol(Rol r) {
        this.rol.add(r);
    }
}
