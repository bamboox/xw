package com.ace.repository;


import com.ace.common.ReadOnlyPagingAndSortingRepository;
import com.ace.entity.Permission;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PermissionRepository extends ReadOnlyPagingAndSortingRepository<Permission, String> {

    List<Permission> getById(Integer id);

    @Query("select p from Permission as p left join p.permissionRole as pr left join pr.role as r left join r.ru as u where u.id = ?1")
    @Modifying
    List<Permission> getByUserId(Integer id);
}