package com.radianbroker.repository;

import com.radianbroker.entity.Report;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends CrudRepository<Report, Long>, JpaSpecificationExecutor<Report> {

	@Query(value = "SELECT * FROM reports r WHERE r.report_id = ?1", nativeQuery = true)
	Report getReport(long reportId);
}
