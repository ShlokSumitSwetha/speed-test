package com.internet.speed.test.dao;

import com.internet.speed.test.domain.InternetSpeedDomain;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * This class is Dao for persisting the speed results
 * @author Sumith Ksheerasagar
 */
@Repository
public interface InternetSpeedDao extends JpaRepository<InternetSpeedDomain, String> {

  List<InternetSpeedDomain> findAll();

  @Modifying
  @Query(value = "INSERT INTO internet_speed (id, timestamp,speed, unit) VALUES (:id, :timestamp,:speed, :unit)", nativeQuery = true)
  @Transactional
  void addSpeed(@Param("id") String id, @Param("timestamp") String timestamp, @Param("speed") double speed,@Param("unit") String unit);

}
