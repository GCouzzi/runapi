package br.gsalles.runapi.controller;

import br.gsalles.runapi.dto.LocationDTO;
import br.gsalles.runapi.mapper.LocationMapper;
import br.gsalles.runapi.mapper.PageMapper;
import br.gsalles.runapi.rdto.LocationResponseDTO;
import br.gsalles.runapi.service.LocationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/locations")
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;
    private final LocationMapper locationMapper;
    private final PageMapper pageMapper;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<LocationResponseDTO> create(@RequestBody @Valid LocationDTO location) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        locationMapper.locationToLocationResponseDTO(locationService.save(location))
                );
    }

    @GetMapping(path = "name", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<LocationResponseDTO> findByName(@RequestParam(name = "name") String name) {
        return ResponseEntity.ok(locationMapper.locationToLocationResponseDTO(locationService.findByName(name)));
    }

    @GetMapping(path = "{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<LocationResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(locationMapper.locationToLocationResponseDTO(locationService.findById(id)));
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Page<LocationResponseDTO>> findAll(@RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "10") int size,
                                                             @RequestParam(defaultValue = "fullname") String sort,
                                                             @RequestParam(defaultValue = "asc") String direction) {
        return ResponseEntity.ok(pageMapper.pageLocationToPageLocationResponseDTO(locationService.findAll(page, size, sort, direction)));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        locationService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "{id}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody @Valid LocationDTO location) {
        locationService.update(id, location);
        return ResponseEntity.noContent().build();
    }
}
