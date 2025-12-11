package com.radianbroker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.radianbroker.entity.RisUser;

@Repository
public interface RisUserRepository extends JpaRepository<RisUser, Long> {

}
