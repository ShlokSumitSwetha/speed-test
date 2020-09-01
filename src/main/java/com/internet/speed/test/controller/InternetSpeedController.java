package com.internet.speed.test.controller;

import com.internet.speed.test.domain.InternetSpeedDomain;
import com.internet.speed.test.dto.InternetSpeedDto;
import com.internet.speed.test.mapper.InternetSpeedMapper;
import com.internet.speed.test.service.InternetSpeedService;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is Rest api for evaluating the internet speeds
 * @author Sumith Ksheerasagar
 */
@RestController
public class InternetSpeedController {

  @Autowired
  private InternetSpeedService internetSpeedService;

  @Autowired
  private InternetSpeedMapper internetSpeedMapper;

  /**
   * This method returns all the internet speeds evaluated
   * @return list of internet speeds
   */
  @GetMapping(value="/getAllInternetSpeeds")
  public List getInternetSpeeds(){
    List<InternetSpeedDomain> internetSpeedDomains=  internetSpeedService.getInternetSpeeds();
    if(!ObjectUtils.isEmpty(internetSpeedDomains)) {
      return internetSpeedDomains.stream().map(internetSpeedMapper::domainToDto)
          .collect(Collectors.toList());
    }
    return Collections.EMPTY_LIST;
  }

  /**
   * This method returns the internet speed for that instance
   * @return Internet Speed Details
    */
  @GetMapping(value="/getInternetSpeed")
  public InternetSpeedDto getInternetSpeed() {
    InternetSpeedDomain internetSpeedDomain = internetSpeedService.getInternetSpeed();
    if (!ObjectUtils.isEmpty(internetSpeedDomain)) {
      return internetSpeedMapper.domainToDto(internetSpeedService.getInternetSpeed());
    }
    return null;
  }
}
