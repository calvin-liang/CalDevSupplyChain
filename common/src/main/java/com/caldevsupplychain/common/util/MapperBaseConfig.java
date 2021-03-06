package com.caldevsupplychain.common.util;

import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;


@MapperConfig(
		componentModel = "spring",
		unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface MapperBaseConfig {
}
