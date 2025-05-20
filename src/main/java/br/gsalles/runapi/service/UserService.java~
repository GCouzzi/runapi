package br.gsalles.runapi.service;

import br.gsalles.runapi.dto.EmailDTO;
import br.gsalles.runapi.dto.PasswordDTO;
import br.gsalles.runapi.dto.UserDTO;
import br.gsalles.runapi.exception.EmailAlreadyExistsException;
import br.gsalles.runapi.exception.EntityNotFoundException;
import br.gsalles.runapi.exception.InvalidCurrentPasswordException;
import br.gsalles.runapi.exception.InvalidPasswordChangeException;
import br.gsalles.runapi.mapper.UserMapper;
import br.gsalles.runapi.model.User;
import br.gsalles.runapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional
    public User create(UserDTO userDTO) {
        User user = userMapper.userDTOToUser(userDTO);
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("User not found")
        );
    }

    @Transactional(readOnly = true)
    public Page<User> findAll(int page, int size, String sort, String direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.fromString(direction), sort);
        return userRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(
                () -> new EntityNotFoundException("User not found")
        );
    }

    @Transactional
    public void updatePassword(PasswordDTO passwordDTO, Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("User not found")
        );
        if(!passwordDTO.getCurrentPassword().equals(user.getPassword())) {
            throw new InvalidCurrentPasswordException("Current password incorrect");
        }
        if(passwordDTO.getCurrentPassword().equals(passwordDTO.getNewPassword())) {
            throw new InvalidPasswordChangeException("New password can't be the same as current password");
        }
        if(!passwordDTO.getNewPassword().equals(passwordDTO.getConfirmNewPassword())) {
            throw new InvalidPasswordChangeException("Passwords don't match");
        }
        user.setPassword(passwordDTO.getNewPassword());
    }

    @Transactional
    public void updateEmail(EmailDTO emailDTO, Long id) {
        if(userRepository.findByEmail(emailDTO.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email already exists");
        }

        User user = userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("User not found")
        );

        if(!emailDTO.getPassword().equals(user.getPassword())) {
            throw new InvalidCurrentPasswordException("Password incorrect");
        }
        user.setEmail(emailDTO.getEmail());
    }

    @Transactional(readOnly = true)
    public Page<User> findContainsName(String name, int page, int size, String sort, String direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.fromString(direction), sort);
        return userRepository.findByFullnameContains(name, pageable);
    }

    @Transactional
    public void delete(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("User not found")
        );
        userRepository.delete(user);
    }
}
