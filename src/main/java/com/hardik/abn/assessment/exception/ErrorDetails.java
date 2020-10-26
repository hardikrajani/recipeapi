package com.hardik.abn.assessment.exception;

import java.util.Date;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class ErrorDetails {
	private Date timestamp;
	private String message;
	private String details;
}