package com.javacodeex.u_future.domain.dto.response.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseForError {
    private String message;
    private long timestamp;
}