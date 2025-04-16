package br.gsalles.runapi.mapper;

import br.gsalles.runapi.model.Location;
import br.gsalles.runapi.model.Run;
import br.gsalles.runapi.model.User;
import br.gsalles.runapi.rdto.LocationResponseDTO;
import br.gsalles.runapi.rdto.RunResponseDTO;
import br.gsalles.runapi.rdto.UserResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

@RequiredArgsConstructor
public class PageMapper {

    private final UserMapper userMapper;
    private final LocationMapper locationMapper;
    private final RunMapper runMapper;

    public Page<UserResponseDTO> pageUserToPageUserResponseDTO(Page<User> page) {
        return page.map(userMapper::userToUserResponseDTO);
    }

    public Page<LocationResponseDTO> pageLocationToPageLocationResponseDTO(Page<Location> page) {
        return page.map(locationMapper::locationToLocationResponseDTO);
    }

    public Page<RunResponseDTO> pageRunToPageRunResponseDTO(Page<Run> page) {
        return page.map(runMapper::runToRunResponseDTO);
    }

}
