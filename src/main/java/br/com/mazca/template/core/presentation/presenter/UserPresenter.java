package br.com.mazca.template.core.presentation.presenter;

import br.com.mazca.template.core.domain.entity.User;
import br.com.mazca.template.core.domain.usecase.UserUseCase;
import br.com.mazca.template.core.presentation.dto.UserDTO;
import br.com.mazca.template.core.presentation.model.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public final class UserPresenter {

    private final UserUseCase userUseCase;

    @Autowired
    public UserPresenter(final UserUseCase userUseCase) {
        this.userUseCase = userUseCase;
    }

    public ResponseEntity<List<UserDTO>> getAllUsers() {
        final List<UserDTO> userDTOs = userUseCase.findAll()
                .stream()
                .map(UserDTO::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(userDTOs);
    }

    public ResponseEntity<UserDTO> getUserById(final UUID userId) {
        return userUseCase.findById(userId)
                .map(user -> ResponseEntity.ok(UserDTO.toDTO(user)))
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<UserDTO> saveUser(final UserDTO userDTO) {
        final User savedUser = userUseCase.save(userDTO);
        final UserDTO savedUserDTO = UserDTO.toDTO(savedUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUserDTO);
    }

    public ResponseEntity<Void> deleteUser(final UUID userId) {
        userUseCase.delete(userId);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<LoginResponse> login(final String email, final String senha) {
        try {
            final LoginResponse loginResponse = userUseCase.login(email, senha);
            return ResponseEntity.ok(loginResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
}
