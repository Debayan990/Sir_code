package org.reni.service;

import org.reni.dtos.LocationDto;

import java.util.List;

public interface LocationService {
    List<LocationDto> getAllLocations();
    LocationDto addLocation(LocationDto locationDto);
    LocationDto getLocationById(int locationId);
}
