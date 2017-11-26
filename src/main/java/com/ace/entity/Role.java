package com.ace.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "role")
public class Role implements Comparable<Role> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private Integer roleLevel;
    private String description;
    private String menuItems;
    @OneToMany(mappedBy = "role",cascade=CascadeType.ALL)
    private Set<PermissionRole> permissionRole = new HashSet<PermissionRole>();

    @OneToMany(mappedBy = "role",cascade=CascadeType.ALL)
    private Set<RoleUser> ru = new HashSet<RoleUser>();

    @Override
    public int compareTo(Role o) {
        if (id == o.getId()) {
            return 0;
        } else if (id > o.getId()) {
            return 1;
        } else {
            return -1;
        }
    }
}