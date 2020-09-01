package com.internet.speed.test.service;

import com.internet.speed.test.domain.InternetSpeedDomain;
import java.util.List;

/**
 * Interface for InternetSpeed
 * @author Sumith Ksheerasagar
 */
public interface InternetSpeedService {

  List<InternetSpeedDomain> getInternetSpeeds();
  InternetSpeedDomain getInternetSpeed();
  InternetSpeedDomain evaluateInternetSpeed();
}
