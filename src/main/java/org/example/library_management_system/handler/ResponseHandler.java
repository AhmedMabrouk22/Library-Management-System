package org.example.library_management_system.handler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.library_management_system.utils.HttpStatusMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;



@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseHandler {

        private HttpStatusMessage status;
        private String message;
        private Object data;

        public static ResponseEntity<ResponseHandler> generateResponse(HttpStatusMessage status,HttpStatusCode statusCode, String message, Object data) {
            ResponseHandler response = new ResponseHandler();
            response.setStatus(status);
            response.setMessage(message);
            response.setData(data);
            return ResponseEntity.status(statusCode).body(response);
        }

}
