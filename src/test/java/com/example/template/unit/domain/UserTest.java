package com.example.template.unit.domain;

import com.example.template.core.domain.entity.User;
import com.example.template.core.presentation.dto.UserDTO;
import com.example.template.shared.helpers.validators.entities.UUIDValidator;
import com.example.template.shared.helpers.validators.errors.ValidationUtil;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Classe responsável por testar a Entidade User e o UserDTO.
 * */
final class UserTest {
    private final UserDTO globalUser = new UserDTO();
    private Validator validator;

    @BeforeEach
    public void setUp() {
        try (final ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }

        globalUser.setName("Alessandro Lima");
        globalUser.setEmail("alessandro.lima@mazca.com.br");
        globalUser.setPassword("123456");
    }

    @Test
    public void testUser() {
        final User user = new User();

        final UUID userId = UUID.randomUUID();
        user.setUser_id(userId);
        user.setName("Flavio Carvalho");
        user.setEmail("flavio.carvalho@mazca.com.br");
        user.setPassword("123456");


        assertEquals(userId, user.getUser_id());
        assertEquals("Flavio Carvalho", user.getName());
        assertEquals("flavio.carvalho@mazca.com.br" , user.getEmail());
        assertEquals("123456", user.getPassword());
    }

    @Test
    public void testInvalidUserId() {
        final String userId = !UUIDValidator
                .validateUUID("userId", "123e4567-e89b-12d3-a456-42661417400")
                ? "UUID inválido" : "UUID válido";
        assertEquals("UUID inválido", userId);
    }

    @Test
    public void testInvalidName() {
        // Teste 1: Nome com caracteres inválidos
        globalUser.setName("123Alessandro Lima");
        final Map<String, String> violationMessages = ValidationUtil.validateAndGetViolations(validator, globalUser);
        assertEquals("Name must start with a letter, cannot contain numbers, and can include accented letters and spaces.", violationMessages.get("name"));
    }

    @Test
    public void testInvalidEmail() {
        // Teste 1: Email inválido
        globalUser.setEmail("alessandroemail.com");
        final Map<String, String> violationMessages = ValidationUtil.validateAndGetViolations(validator, globalUser);
        assertEquals("E-mail must be valid", violationMessages.get("email"));
    }

    @Test
    public void testInvalidPassword() {
        // Teste 1: Senha em branco
        globalUser.setPassword("      ");
        Map<String, String> violationMessages = ValidationUtil.validateAndGetViolations(validator, globalUser);
        assertEquals("Password is required", violationMessages.get("password"));

        // Teste 2: Senha com menos de 6 caracteres
        globalUser.setPassword("12345");
        violationMessages = ValidationUtil.validateAndGetViolations(validator, globalUser);
        assertEquals("Password must be between 6 and 75 characters", violationMessages.get("password"));
    }

    @Test
    public void testToEntity() {
        final User userEntity = globalUser.toEntity();

        assertNotNull(userEntity, "A entidade User não deve ser null.");
        assertEquals(globalUser.getName(), userEntity.getName(), "O nome deve ser igual na entidade.");
        assertEquals(globalUser.getEmail(), userEntity.getEmail(), "O e-mail deve ser igual na entidade.");
        assertEquals(globalUser.getPassword(), userEntity.getPassword(), "A senha deve ser igual na entidade.");
    }

    @Test
    public void testToDTO() {
        final User userEntity = globalUser.toEntity();
        final UserDTO newUserDTO = UserDTO.toDTO(userEntity);

        assertNotNull(newUserDTO, "O UserDTO não deve ser null.");
        assertEquals(userEntity.getName(), newUserDTO.getName(), "O nome deve ser igual no DTO.");
        assertEquals(userEntity.getEmail(), newUserDTO.getEmail(), "O e-mail deve ser igual no DTO.");
    }
}
