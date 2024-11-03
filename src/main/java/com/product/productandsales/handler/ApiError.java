package com.product.productandsales.handler;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.product.productandsales.constants.AppConstants;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ApiError {
    @JsonIgnore
    private HttpStatus status;

    private String message;

    private String code;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = AppConstants.DATE_FORMAT)
    private LocalDateTime timestamp;

    public ApiError(final HttpStatus status, final String code, final String message, LocalDateTime timestamp) {
        super();
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
        this.code = code;
    }
}
