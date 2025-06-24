package com.ecole221.l3dev.gestion.scolarite.exception;
//ceci est un test
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.converter.HttpMessageNotReadableException;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
//@RestControllerAdvice
public class GlobalExceptionHandler {

    private String detectJsonFieldFromMessage(String message) {
        // Exemple : "Cannot deserialize value of type `java.lang.Integer` from String \"xyz\" (field: 'fraisInscription')"
        if (message != null && message.contains("from")) {
            int idx = message.indexOf("` from");
            if (idx != -1 && message.contains("field")) {
                int fieldIdx = message.indexOf("field: ");
                if (fieldIdx != -1) {
                    int start = message.indexOf("'", fieldIdx);
                    int end = message.indexOf("'", start + 1);
                    if (start != -1 && end != -1) {
                        return message.substring(start + 1, end);
                    }
                }
            }
        }
        return "corps JSON";
    }


    // Validation (annotations @NotNull, @Min, etc.)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<FieldErrorDetail> fieldErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> new FieldErrorDetail(err.getField(), err.getDefaultMessage()))
                .collect(Collectors.toList());

        BadRequestException customException = new BadRequestException("Erreur de validation", fieldErrors);
        return ResponseEntity
                .status(customException.getStatus())
                .body(Map.of(
                        "code", customException.getStatus().value(),
                        "status", customException.getStatus().name(),
                        "message", customException.getMessage(),
                        "errors", customException.getErrors()
                ));
    }

    // JSON malformé ou valeur de type incorrect (ex: entier trop grand)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleInvalidJson(HttpMessageNotReadableException ex) {
        String fullMessage = ex.getMostSpecificCause().getMessage(); // message technique de Jackson

        // Valeurs par défaut
        String field = "corps JSON";
        String value = null;

        // Exemple de message Jackson :
        if (fullMessage != null && fullMessage.contains("Numeric value")) {
            int start = fullMessage.indexOf('(');
            int end = fullMessage.indexOf(')', start);
            if (start != -1 && end != -1) {
                value = fullMessage.substring(start + 1, end); // extrait la valeur fautive
            }

            field = detectJsonFieldFromMessage(ex.getMessage());
        }

        String finalMessage = value != null
                ? "Valeur invalide : " + value + " (trop grande pour le type attendu)"
                : "Format JSON invalide ou valeur incorrecte (type ou taille)";

        FieldErrorDetail errorDetail = new FieldErrorDetail(field, finalMessage);
        BadRequestException customException = new BadRequestException("Erreur de parsing JSON", List.of(errorDetail));

        return ResponseEntity
                .status(customException.getStatus())
                .body(Map.of(
                        "code", customException.getStatus().value(),
                        "status", customException.getStatus().name(),
                        "message", customException.getMessage(),
                        "errors", customException.getErrors()
                ));
    }


    // autres erreurs génériques
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleOtherErrors(Exception ex) {
        return ResponseEntity
                .internalServerError()
                .body(Map.of(
                        "code", 500,
                        "status", "INTERNAL_SERVER_ERROR",
                        "message", ex.getMessage()
                ));
    }
}
