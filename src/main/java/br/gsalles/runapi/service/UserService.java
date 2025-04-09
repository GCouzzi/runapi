package br.gsalles.runapi.service;

import br.gsalles.runapi.dto.EmailDTO;
import br.gsalles.runapi.dto.PasswordDTO;
import br.gsalles.runapi.dto.UserDTO;
import br.gsalles.runapi.mapper.UserMapper;
import br.gsalles.runapi.model.User;
import br.gsalles.runapi.rdto.UserResponseDTO;
import br.gsalles.runapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
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
                () -> new RuntimeException("User not found")
        );
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("User not found")
        );
    }

    @Transactional
    public void updatePassword(PasswordDTO passwordDTO, Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User not found")
        );
        if(passwordDTO.getCurrentPassword().equals(passwordDTO.getNewPassword())) {
            throw new RuntimeException("New password can't be the same as current password");
        }
        if(!passwordDTO.getCurrentPassword().equals(user.getPassword())) {
            throw new RuntimeException("Current password incorrect");
        }
        if(!passwordDTO.getNewPassword().equals(passwordDTO.getConfirmNewPassword())) {
            throw new RuntimeException("Passwords don't match");
        }
        user.setPassword(passwordDTO.getNewPassword());
    }

    @Transactional
    public void updateEmail(EmailDTO emailDTO, Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User not found")
        );
        if(!emailDTO.getPassword().equals(user.getPassword())) {
            throw new RuntimeException("Password incorrect");
        }
        user.setEmail(emailDTO.getEmail());
    }

    @Transactional(readOnly = true)
    public List<User> findContainsName(String name) {
        return userRepository.findByFullnameContains(name);
    }

    @Transactional
    public void delete(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User not found")
        );
        userRepository.delete(user);
    }
}
