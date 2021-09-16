package com.example.exception;

 import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResponseDto {
	public String message;
	public Object data;
	public Boolean success;
}
