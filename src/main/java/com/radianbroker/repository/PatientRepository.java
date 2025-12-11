package com.radianbroker.repository;

import com.radianbroker.entity.Patient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends CrudRepository<Patient, Long> {
	@Query(value = "SELECT * FROM patients p WHERE p.id = ?1", nativeQuery = true)
	Patient findByPatientId(Long patientId);
}
