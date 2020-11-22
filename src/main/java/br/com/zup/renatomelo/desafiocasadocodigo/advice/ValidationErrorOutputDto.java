package br.com.zup.renatomelo.desafiocasadocodigo.advice;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorOutputDto {

    private List<String> globalErrorMessages = new ArrayList<>();
    private List<FieldErrorOutputDto> fieldErrorOutputDtoList = new ArrayList<>();

    public void addError(String message) {
        globalErrorMessages.add(message);
    }

    public void addFieldError(String field, String message) {
        FieldErrorOutputDto fieldErrorOutputDto = new FieldErrorOutputDto(field, message);
        this.fieldErrorOutputDtoList.add(fieldErrorOutputDto);
    }

    public List<String> getGlobalErrorMessages() {
        return this.globalErrorMessages;
    }

    public List<FieldErrorOutputDto> getFieldErrorOutputDtoList() {
        return fieldErrorOutputDtoList;
    }

    public int getNumberOfErrors() {
        return this.globalErrorMessages.size() + this.fieldErrorOutputDtoList.size();
    }
}
