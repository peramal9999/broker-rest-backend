package com.radianbroker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.radianbroker.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	@Query(value = "SELECT r.* FROM  roles r, user_roles ur WHERE r.role_id = ur.role_id AND ur.user_id=?1", nativeQuery = true)
	List<Role> findByUserId(Long userId);
}
