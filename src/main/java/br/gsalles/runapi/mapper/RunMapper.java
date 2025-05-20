package br.gsalles.runapi.mapper;

import br.gsalles.runapi.model.Run;
import br.gsalles.runapi.rdto.RunResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RunMapper {

    @Mappings({
            @Mapping(target = "startLocationName", source = "startLocation.name"),
            @Mapping(target = "endLocationName", source = "endLocation.name"),
            @Mapping(target = "userEmail", source = "user.email"),
            @Mapping(target = "distance", source = "distance")
    })
    RunResponseDTO runToRunResponseDTO(Run run);

    List<RunResponseDTO> listRunToListRunResponseDTO(List<Run> runs);

}
