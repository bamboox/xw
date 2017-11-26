package com.ace.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String cnname;
    private String username;
//    @JsonIgnore
    private String password;
    private String rePassword;
    private String historyPassword;
    private String email;
    @JsonIgnore
    private String telephone;
    private String mobilePhone;
    @Transient
    private List<? extends GrantedAuthority> authorities;
    @OneToMany(mappedBy = "user",cascade=CascadeType.ALL)
    private Set<RoleUser> roleUser = new HashSet<RoleUser>();

    @Override
    @JsonIgnore
    @Transient
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    @Transient
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    @Transient
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    @Transient
    public boolean isEnabled() {
        return true;
    }

}