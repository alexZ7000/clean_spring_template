package br.com.mazca.template.core.presentation.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class LoginRequest {
    private String email;
    private String password;
}
