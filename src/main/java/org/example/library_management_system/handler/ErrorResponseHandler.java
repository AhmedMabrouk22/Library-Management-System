package org.example.library_management_system.handler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.library_management_system.utils.HttpStatusMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseHandler {
    private HttpStatusMessage status;
    private String message;

    public static ResponseEntity<ErrorResponseHandler> generateResponse(HttpStatusMessage status, HttpStatus statusCode, String message) {
        ErrorResponseHandler response = new ErrorResponseHandler();
        response.setStatus(status);
        response.setMessage(message);
        return ResponseEntity.status(statusCode).body(response);
    }
}
