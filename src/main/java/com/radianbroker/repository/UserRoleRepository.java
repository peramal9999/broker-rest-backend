package com.radianbroker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.radianbroker.entity.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

	@Query(value = "SELECT * FROM user_roles WHERE user_id=?1", nativeQuery = true)
	List<UserRole> findAllByUserId(Long userId);

	@Query(value = "SELECT ur.user_id FROM `user_roles` ur,roles r WHERE ur.role_id= r.role_id and r.name=?1", nativeQuery = true)
	List<Long> findByRole(String sessionRole);
	
}
