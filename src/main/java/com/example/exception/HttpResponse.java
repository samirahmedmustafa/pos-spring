package com.example.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HttpResponse {
	private Integer httpStatusCode;
	private HttpStatus httpStatus;
	private String reason;
	private String message;
	private Date messageDate;
}
