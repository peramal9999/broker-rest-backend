package com.radianbroker.repository;

import com.radianbroker.entity.HL7Received;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface HL7ReceivedRepository
		extends CrudRepository<HL7Received, Long>, JpaSpecificationExecutor<HL7Received> {

//	@Query(value = "SELECT count(*) FROM hl7_received h WHERE h.STATUS = ?1", nativeQuery = true)
//	Integer messageCount(String status);
//
//	Page<HL7Received> findAll(Specification<HL7Received> spec, Pageable pageable);
//
//	@Query(value = "SELECT * FROM hl7_received h WHERE h.STATUS = ?1 AND"
//            + " CONVERT_TZ(h.created_date, '+00:00', ?2) <= CONVERT_TZ(NOW(), '+00:00', ?2) - INTERVAL 24 HOUR ", nativeQuery = true)
//	List<HL7Received> findByTimestampBefore(String status,String timeZone);
//
	@Query(value = "SELECT * FROM hl7_received h  where  (?1 IS NULL OR h.created_date >= ?1) " +
		       "      AND (?2 IS NULL OR h.created_date <= ?2) " +
		       "      AND IFNULL(( h.type in (?3 )), 1) " +
		       "      AND IFNULL(( h.mip in (?4 )), 1) " +
		       "      AND (?5 IS NULL OR h.order_no = ?5) " +
		       "      AND (?6 IS NULL OR h.ris_id = ?6)     " +
		        "      AND IFNULL(( h.status in (?7 )), 1)  order by created_date desc ", countQuery ="SELECT count(*) FROM hl7_received h where  (?1 IS NULL OR h.created_date >= ?1) " +
		       "       AND (?2 IS NULL OR h.created_date <= ?2) " +
		       "       AND IFNULL(( h.type in (?3 )), 1) " +
		       "       AND IFNULL(( h.mip in (?4 )), 1) " +
		       "       AND (?5 IS NULL OR h.order_no = ?5) " +
		       "       AND (?6 IS NULL OR h.ris_id = ?6) " +
		       "       AND IFNULL(( h.status in (?7 )), 1) ", nativeQuery = true)
		   Page<HL7Received> findByFilterAll(Date startDate,
		                           Date endDate,
		                           List<String> messageTypes,
		                           List<String> mips,
		                           String orderNo,
		                           Long risId,
		                           List<String> statuses, Pageable paging);
//	@Query(value = "SELECT * " +
//			" FROM hl7_received " +
//			" WHERE order_no = ?1 AND " +
//			" STR_TO_DATE(" +
//			"   SUBSTRING_INDEX(SUBSTRING_INDEX(REPLACE(directory_path, '\\\\', '/'), '/', 4), '/', -1)," +
//			"   '%Y%m%d'" +
//			" ) < STR_TO_DATE(?2, '%Y%m%d')",
//			nativeQuery = true)
//	List<HL7Received> findOldHl7ByOrderNo(String orderNo, String weekAgo);

}
