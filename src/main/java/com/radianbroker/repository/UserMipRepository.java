package com.radianbroker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.radianbroker.entity.UserMip;

@Repository
public interface UserMipRepository extends JpaRepository<UserMip, Long> {

}
