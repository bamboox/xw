package com.ace.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "permission")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String permissionUrl;
    private String method;
    private String description;

    @OneToMany(mappedBy = "permission",cascade=CascadeType.ALL)
    private Set<PermissionRole> permissionRole = new HashSet<PermissionRole>();
}