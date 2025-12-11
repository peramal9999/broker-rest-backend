package com.radianbroker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.radianbroker.entity.UserGroup;

@Repository
public interface UserGroupRepository extends JpaRepository<UserGroup, Long> {

	@Query(value = "SELECT `group_id` FROM `user_groups` WHERE `user_id`=?1", nativeQuery = true)
	List<Long> findByUserId(Long userId);

}
