package com.example.estudio.exception;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import java.util.List;

public class ExcepcionContadorIncorrecto extends RuntimeException {

    private List<FieldError> errores;
    // Guarda en una lista los errores de validación

    public ExcepcionContadorIncorrecto(BindingResult result) {
        this.errores = result.getFieldErrors();
    }
    public List<FieldError> getErrores() {
        return errores;
    }
}
