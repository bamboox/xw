package com.ace.repository;


import com.ace.common.ReadOnlyPagingAndSortingRepository;
import com.ace.entity.User;

public interface UserRepository extends ReadOnlyPagingAndSortingRepository<User, String> {
	User getById(Integer id);
	User getByUsername(String name);
}