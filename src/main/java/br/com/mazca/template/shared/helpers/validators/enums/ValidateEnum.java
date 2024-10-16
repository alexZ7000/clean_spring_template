package br.com.mazca.template.shared.helpers.validators.enums;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ValidateEnum {
    Class<? extends Enum<?>> enumClass();
    String message() default "Valor não encontrado para o Enum especificado";
}

