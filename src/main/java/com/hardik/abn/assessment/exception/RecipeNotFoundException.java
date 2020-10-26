package com.hardik.abn.assessment.exception;

/**
 * The Class RecipeNotFoundException.
 */
public class RecipeNotFoundException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6064040876968520428L;

	/**
	 * Instantiates a new recipe not found exception.
	 *
	 * @param message the message
	 */
	public RecipeNotFoundException(String message) {
		super(message);
	}

}
