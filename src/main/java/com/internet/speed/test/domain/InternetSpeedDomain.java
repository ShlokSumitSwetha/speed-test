package com.internet.speed.test.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Domain for Internet Speed
 * @author Sumith Ksheerasagar
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "internet_speed")
public class InternetSpeedDomain {

  @Id
  @Column(name = "id", unique = true)
  private String id;
  private String timestamp;
  private double speed;
  private String unit;

}
