package com.radianbroker.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.radianbroker.entity.Mip;

import java.util.List;

@Repository
public interface MipRepository extends JpaRepository<Mip, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM mips where ris_id=?1")
    List<Mip> findAllByRisId(long id);

}
