package com.pdp.springm10.handler;

import com.pdp.springm10.dto.ErrorMessageDTO;
import com.pdp.springm10.handler.exception.UserNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Aliabbos Ashurov
 * @since 14/September/2024  12:32
 **/
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = Logger.getLogger(GlobalExceptionHandler.class.getName());

    @Value("${app.log-mode}")
    private boolean LOG_MODE;

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorMessageDTO> handleUserNotFoundException(UserNotFoundException ex, HttpServletRequest request) {
        ErrorMessageDTO error = ErrorMessageDTO.of(
                "USER_NOT_FOUND",
                ex.getMessage(),
                request.getRequestURI()
        );
        logError(ex, request);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorMessageDTO> handleGenericException(Exception ex, HttpServletRequest request) {
        ErrorMessageDTO error = ErrorMessageDTO.of(
                "INTERNAL_SERVER_ERROR",
                "An unexpected error occurred.",
                request.getRequestURI()
        );
        logError(ex, request);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    private void logError(Exception ex, HttpServletRequest request) {
        if (LOG_MODE) {
            logger.log(Level.WARNING, "Exception occurred: ", ex);
            logger.log(Level.WARNING, "Request URI: {}", request.getRequestURI());
        }
    }
}
