package org.reni.controller;

import lombok.RequiredArgsConstructor;
import org.reni.dtos.LocationDto;
import org.reni.service.LocationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/locations")
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;

    @GetMapping
    public ResponseEntity<?> getAllLocations() {
        return ResponseEntity.ok(locationService.getAllLocations());
    }

    @PostMapping
    public ResponseEntity<?> addLocation(@RequestBody LocationDto locationDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(locationService.addLocation(locationDto));
    }
    @GetMapping("/{id}")
    public ResponseEntity<LocationDto> getLocationById(@PathVariable int id,@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(locationService.getLocationById(id));
    }
}
