package com.example.estudio.exception;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import java.util.List;


// Excepción personalizada que nosotros definimos
// Spring tiene su propio sistema de validación, pero cuando
//      la validación falla, guarda los errores en un objeto BindingResult.
//      El problema es que ese objeto no se puede lanzar como excepción directamente.
//      Entonces creas esta clase para envolver esos errores y poder lanzarlos.


public class ExcepcionContadorIncorrecto extends RuntimeException {

    private List<FieldError> errores;
    // Guarda en una lista los errores de validación

    public ExcepcionContadorIncorrecto(BindingResult result) {
        this.errores = result.getFieldErrors();
    }
    // BindingResult es donde Spring guarda los errores automaticamente
    // El constructor recibe el BindingResult de Spring y extrae los campos de errores
    public List<FieldError> getErrores() {
        return errores;
    }
}
