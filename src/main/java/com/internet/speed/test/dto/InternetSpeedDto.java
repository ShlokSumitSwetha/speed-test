package com.internet.speed.test.dto;
import lombok.Data;

/**
 * DTO
 * @author Sumith Ksheerasagar
 */
@Data
public class InternetSpeedDto {

  private String id;
  private String timestamp;
  private double speed;
  private String unit;
}
