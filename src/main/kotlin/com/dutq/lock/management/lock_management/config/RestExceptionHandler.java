package com.dutq.lock.management.lock_management.config;

import com.dutq.lock.management.lock_management.dtos.ErrorDto;
import com.dutq.lock.management.lock_management.exceptions.AppException;
import com.dutq.lock.management.lock_management.exceptions.EUAPIRestException;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class RestExceptionHandler {
  @ExceptionHandler(value = {AppException.class})
  @ResponseBody
  public ResponseEntity<ErrorDto> handleException(AppException ex) {
    log.error("Error: ", ex);
    return ResponseEntity.status(ex.getStatus())
        .body(ErrorDto.builder().message(ex.getMessage()).build());
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<String> handleJsonParseException(Exception ex) {
    log.error("Error: ", ex);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body("{\"error\": \"Invalid JSON\", \"message\": \"" + ex.getMessage() + "\"}");
  }

  @ExceptionHandler(value = EUAPIRestException.class)
  public ResponseEntity<ErrorDto> handleExternalApiException(EUAPIRestException e) {
    log.error("Error: ", e);
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .contentType(MediaType.APPLICATION_JSON)
        .body(
            ErrorDto.builder()
                .message(Objects.nonNull(e) ? e.getMessage() : "Internal Error")
                .build());
  }
}
