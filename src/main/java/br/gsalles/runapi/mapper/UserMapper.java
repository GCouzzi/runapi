package br.gsalles.runapi.mapper;

import br.gsalles.runapi.dto.UserDTO;
import br.gsalles.runapi.model.User;
import br.gsalles.runapi.rdto.UserResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mappings({
            @Mapping(target = "fullname", source = "fullname"),
            @Mapping(target = "email", source = "email"),
            @Mapping(target = "password")
    })
    User userDTOToUser(UserDTO userDTO);

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "fullname", source = "fullname"),
            @Mapping(target = "email", source = "email"),
    })
    UserResponseDTO userToUserResponseDTO(User user);

    List<UserResponseDTO> listUserToListUserResponseDTO(List<User> users);
}
