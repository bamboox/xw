package com.ace.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by bamboo on 17-11-25.
 */
@Data
@Entity
@Table(name = "role_user")
public class RoleUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
