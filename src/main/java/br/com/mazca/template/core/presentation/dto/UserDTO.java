package br.com.mazca.template.core.presentation.dto;

import br.com.mazca.template.core.domain.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class UserDTO {

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 75, message = "Name must be between 3 and 75 characters")
    @Pattern(
            regexp = "^\\p{L}[\\p{L}\\s]*$",
            message = "Name must start with a letter, cannot contain numbers, and can include accented letters and spaces."
    )
    private String name;

    @NotBlank(message = "E-mail is required")
    @Size(min = 3, max = 75, message = "Email must be between 3 and 75 characters")
    @Email(message = "E-mail must be valid")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 75, message = "Password must be between 6 and 75 characters")
    private String password;

    public User toEntity() {
        final User user = new User();
        user.setName(this.name);
        user.setEmail(this.email);
        user.setPassword(this.password);
        return user;
    }

    public static UserDTO toDTO(User user) {
        final UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        return userDTO;
    }
}
