package mazeneko.mazewands.example.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
class CustomExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(CustomException.class)
  ResponseEntity<?> handleException(@NonNull CustomException ex, @NonNull WebRequest request) {
    final var body = ErrorResponseBody.from(ex);
    final var headers = new HttpHeaders();
    final var statusCode = switch (ex) {
      // add your custom exceptions
      case MemberNotFoundException e -> HttpStatus.NOT_FOUND;
    };
    return this.handleExceptionInternal(ex, body, headers, statusCode, request);
  }
}

record ErrorResponseBody(
    String errorCode,
    String description,
    Object contents,
    String message) {
  static ErrorResponseBody from(CustomException ex) {
    return new ErrorResponseBody(
        ex.getErrorCode().value(),
        ex.getErrorCode().description(),
        ex.getContents(),
        ex.getLocalizedMessage());
  }
}