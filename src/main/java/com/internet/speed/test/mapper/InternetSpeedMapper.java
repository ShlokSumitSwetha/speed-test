package com.internet.speed.test.mapper;

import com.internet.speed.test.domain.InternetSpeedDomain;
import com.internet.speed.test.dto.InternetSpeedDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

/**
 * Mapper
 * @author Sumith Ksheerasagar
 */
@Mapper(componentModel="spring")
public interface InternetSpeedMapper {
  @Mappings({})
  InternetSpeedDto domainToDto(InternetSpeedDomain internetSpeedDomain);

}
