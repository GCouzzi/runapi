package br.gsalles.runapi.service;

import br.gsalles.runapi.dto.LocationDTO;
import br.gsalles.runapi.mapper.LocationMapper;
import br.gsalles.runapi.model.Location;
import br.gsalles.runapi.rdto.LocationResponseDTO;
import br.gsalles.runapi.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LocationService {

    private final LocationRepository locationRepository;
    private final LocationMapper locationMapper;

    @Transactional
    public Location save(LocationDTO locationDTO) {
        return locationRepository.save(locationMapper.locationDTOToLocation(locationDTO));
    }

    @Transactional(readOnly = true)
    public Location findByName(String name) {
        return locationRepository.findByName(name).orElseThrow(
                                () -> new RuntimeException("Location not found")
        );
    }

    @Transactional(readOnly = true)
    public Location findById(Long id) {
        return locationRepository.findById(id).orElseThrow(
                        () -> new RuntimeException("Location not found")
        );
    }

    @Transactional(readOnly = true)
    public List<Location> findAll() {
        return locationRepository.findAll();
    }

    @Transactional
    public void delete(Long id) {
        Location location = locationRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Location not found")
        );
        locationRepository.delete(location);
    }

    @Transactional
    public void update(Long id, LocationDTO locationDTO) {
        Location location = locationRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Location not found")
        );
        locationMapper.updateLocationFromDTO(locationDTO, location);
    }
}
