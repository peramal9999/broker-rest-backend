package com.radianbroker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.radianbroker.entity.Ris;

@Repository
public interface RisRepository extends JpaRepository<Ris, Long> {

	List<Ris> findAll();
	
	@Query(value ="SELECT * FROM ris WHERE ris_id= ?1", nativeQuery = true)
	Ris getRisById(Long id);

	@Query(value = "SELECT DISTINCT r.`ris_id` FROM " + 
			       " user_groups AS ug, " + 
			       " mips AS m, " + 
			       " `groups_master` gm, " + 
			       " `ris` r " + 
			       " WHERE " + 
			       " ug.group_id=m.group_id AND " + 
			       " ug.group_id=gm.group_id AND " + 
			       " m.`ris_id`=r.`ris_id` AND " + 
			       " ug.user_id = ?1", nativeQuery = true)
	List<Long> getGroupMemberAllowedRis(Long userId);
}
