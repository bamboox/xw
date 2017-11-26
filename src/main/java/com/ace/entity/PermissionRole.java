package com.ace.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by bamboo on 17-11-25.
 */
@Data
@Entity
@Table(name = "permission_role")
public class PermissionRole {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "permission_id")
    private Permission permission;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
