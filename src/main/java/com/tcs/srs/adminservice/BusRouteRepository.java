package com.tcs.srs.adminservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BusRouteRepository extends JpaRepository<BusRoute, Integer> {

	@Transactional
	@Modifying
	@Query("delete from BusRoute b where b.id = ?1")
	int deleteRouteByBusNumber(Integer id);

}