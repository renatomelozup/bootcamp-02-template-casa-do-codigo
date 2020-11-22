package br.com.zup.renatomelo.desafiocasadocodigo.advice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ValidationErrorHandler {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ValidationErrorOutputDto handleValidationError(MethodArgumentNotValidException exception) {

        List<ObjectError> globalErrors = exception.getBindingResult().getGlobalErrors();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        return buildValidationErrors(globalErrors, fieldErrors);
    }


    private ValidationErrorOutputDto buildValidationErrors(List<ObjectError> globalErrors, List<FieldError> fieldErrors) {

        ValidationErrorOutputDto validationErrorOutputDto = new ValidationErrorOutputDto();

        globalErrors.forEach(error -> validationErrorOutputDto.addError(getErrorMessage(error)));

        fieldErrors.forEach(fieldError -> {
            String errorMessage = getErrorMessage(fieldError);
            validationErrorOutputDto.addFieldError(fieldError.getField(), errorMessage);
        });

        return validationErrorOutputDto;
    }

    private String getErrorMessage(ObjectError error) {
        return messageSource.getMessage(error, LocaleContextHolder.getLocale());
    }
}
