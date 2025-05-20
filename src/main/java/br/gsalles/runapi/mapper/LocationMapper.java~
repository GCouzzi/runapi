package br.gsalles.runapi.mapper;

import br.gsalles.runapi.dto.LocationDTO;
import br.gsalles.runapi.model.Location;
import br.gsalles.runapi.rdto.LocationResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LocationMapper {
    LocationMapper INSTANCE = Mappers.getMapper(LocationMapper.class);

    @Mappings({
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "latitude", source = "latitude"),
            @Mapping(target = "longitude", source = "longitude")
    })
    Location locationDTOToLocation(LocationDTO locationDTO);

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "latitude", source = "latitude"),
            @Mapping(target = "longitude", source = "longitude")
    })
    LocationResponseDTO locationToLocationResponseDTO(Location location);

    List<LocationResponseDTO> listLocationToListLocationResponseDTO(List<Location> locations);

    @Mappings({
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "latitude", source = "latitude"),
            @Mapping(target = "longitude", source = "longitude")
    })
    void updateLocationFromDTO(LocationDTO locationDTO, @MappingTarget Location location);
}
