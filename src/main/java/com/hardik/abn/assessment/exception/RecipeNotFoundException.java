package com.hardik.abn.assessment.exception;

public class RecipeNotFoundException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 6064040876968520428L;

    public RecipeNotFoundException(String message, Object id) {
        super(String.format(message,id));
    }

}
