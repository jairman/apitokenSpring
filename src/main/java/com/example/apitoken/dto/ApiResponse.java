package com.example.apitoken.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;

@Data
@AllArgsConstructor
@Builder
public class ApiResponse<T> {
    private T data;
    private String timestamp;
    private String status;
private int code;

    // Método estático para éxitos
    public static <T> ApiResponse<T> success(T data, HttpStatus httpStatus) {
        return ApiResponse.<T>builder()
                .data(data)
                .timestamp(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .status(httpStatus.getReasonPhrase()) // "OK", "Created", etc.
                .code(httpStatus.value()) // 200, 201, etc.
                .build();
    }

    // Método estático para errores
    public static <T> ApiResponse<T> error(String message, HttpStatus httpStatus) {
        return ApiResponse.<T>builder()
                .data(null)
                .timestamp(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .status(message)
                .code(httpStatus.value())
                .build();
    }

}
