package org.reni.clients;

import org.reni.dtos.LocationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name="location-service",url = "http://localhost:8888/api/locations")
public interface LocationClient {
    @GetMapping("/{id}")
    public ResponseEntity<LocationDto> getLocationById(@PathVariable int id, @RequestHeader("Authorization") String token);
}
