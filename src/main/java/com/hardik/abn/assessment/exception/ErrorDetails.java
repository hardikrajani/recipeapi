package com.hardik.abn.assessment.exception;

import java.util.Date;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class ErrorDetails {

	/** The timestamp. */
	private Date timestamp;

	/** The message. */
	private String message;

	/** The details. */
	private String details;
}