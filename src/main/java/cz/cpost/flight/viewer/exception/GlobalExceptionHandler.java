package cz.cpost.flight.viewer.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.function.client.WebClientResponseException;

/**
 * Global exception handler for the application.
 * This class handles specific exceptions and returns appropriate error pages.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public String handleTypeMismatch(Model model) {
        return "error-type-mismatch"; // Thymeleaf template
    }

    @ExceptionHandler(WebClientResponseException.NotFound.class)
    public String handleNotFound(Model model) {
        return "error-not-found"; // Thymeleaf template
    }

    @ExceptionHandler(WebClientResponseException.TooManyRequests.class)
    public String handleTooManyRequests(Model model) {
        return "error-too-many-requests"; // Thymeleaf template
    }
}
