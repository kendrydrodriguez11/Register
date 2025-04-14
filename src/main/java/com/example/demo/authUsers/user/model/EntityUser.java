package com.example.demo.authUsers.user.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "EntityUser")
public class EntityUser implements UserDetails {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idUser;

    @NotBlank
    private String nameUser;

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotNull
    private LocalDate accountCreating;


    @NotNull
    @ManyToMany(fetch = FetchType.EAGER, targetEntity = EntityRole.class, cascade = CascadeType.PERSIST)
    @JoinTable(name = "UserRoles", joinColumns = @JoinColumn(name = "idUser"), inverseJoinColumns = @JoinColumn(name ="id_roles"))
    private Set<EntityRole> roleUser;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roleUser.stream()
                .map(roleU -> new SimpleGrantedAuthority("ROLE_"+(roleU.getNameRole())))
                .collect(Collectors.toSet());
    }

    @Override
    public String getUsername() {
        return nameUser;
    }

    @Override
    public String getPassword() {
        return password;
    }
}
