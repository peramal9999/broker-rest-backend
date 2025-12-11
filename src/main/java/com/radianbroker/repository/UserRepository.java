package com.radianbroker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.radianbroker.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

	User findByEmail(String email);

	@Query(value = "SELECT * FROM users  WHERE email=?1 AND status=true", nativeQuery = true)
	User findByEmailAndStatus(String username, boolean status);

	User findByUserId(Long userId);

}
