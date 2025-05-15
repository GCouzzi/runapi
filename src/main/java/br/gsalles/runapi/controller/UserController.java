package br.gsalles.runapi.controller;

import br.gsalles.runapi.dto.EmailDTO;
import br.gsalles.runapi.dto.PasswordDTO;
import br.gsalles.runapi.dto.UserDTO;
import br.gsalles.runapi.mapper.PageMapper;
import br.gsalles.runapi.mapper.UserMapper;
import br.gsalles.runapi.model.User;
import br.gsalles.runapi.rdto.UserResponseDTO;
import br.gsalles.runapi.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final PageMapper pageMapper;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<UserResponseDTO> create(@RequestBody @Valid UserDTO user) {
        return ResponseEntity.status(201).body(userMapper.userToUserResponseDTO(userService.create(user)));
    }

    @GetMapping(path = "{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<UserResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(userMapper.userToUserResponseDTO(userService.findById(id)));
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Page<UserResponseDTO>> findAll(@RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "10") int size,
                                                         @RequestParam(defaultValue = "fullname") String sort,
                                                         @RequestParam(defaultValue = "asc") String direction) {
        return ResponseEntity.ok(pageMapper.pageUserToPageUserResponseDTO(userService.findAll(page, size, sort, direction)));
    }

    @GetMapping(path = "email", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<UserResponseDTO> findByEmail(@RequestParam String email) {
        return ResponseEntity.ok(userMapper.userToUserResponseDTO(userService.findByEmail(email)));
    }

    @GetMapping(path = "name", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Page<UserResponseDTO>> findContainsName(@RequestParam String name,
                                                                  @RequestParam(defaultValue = "0") int page,
                                                                  @RequestParam(defaultValue = "10") int size,
                                                                  @RequestParam(defaultValue = "fullname") String sort,
                                                                  @RequestParam(defaultValue = "asc") String direction) {
        return ResponseEntity.ok(pageMapper.pageUserToPageUserResponseDTO(userService.findContainsName(name, page, size, sort, direction)));
    }

    @PatchMapping(path = "password/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Void> updatePassword(@RequestBody @Valid PasswordDTO passwordDTO, @PathVariable Long id) {
        userService.updatePassword(passwordDTO, id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(path = "email/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Void> updateEmail(@RequestBody @Valid EmailDTO emailDTO, @PathVariable Long id) {
        userService.updateEmail(emailDTO, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<User> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}