package org.reni.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.reni.dtos.LocationDto;
import org.reni.entities.Location;
import org.reni.repository.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
@Slf4j
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;
    private final ModelMapper modelMapper;
    @Override
    public List<LocationDto> getAllLocations() {
      
        List<Location> locations=
                locationRepository.findAll();
        return locations.stream().map(location->
                modelMapper.map(location, LocationDto.class)).toList();
    }



    @Override
    public LocationDto addLocation(LocationDto locationDto) {

        var location=
                modelMapper.map(locationDto, Location.class);
        var savedLocation=
                locationRepository.save(location);
        return modelMapper.map(savedLocation, LocationDto.class);
    }

    @Override
    public LocationDto getLocationById(int locationId) {

        return locationRepository.findById(locationId)
                .map(location -> modelMapper.map(location, LocationDto.class))
                .orElseThrow(()->new RuntimeException("Location not found"));
    }
}
