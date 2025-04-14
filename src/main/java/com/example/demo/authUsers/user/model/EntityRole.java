package com.example.demo.authUsers.user.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Entity(name = "EntityRole")
public class EntityRole {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idRole;

    @NotNull
    @Enumerated(EnumType.STRING)
    private RoleU nameRole;


}
